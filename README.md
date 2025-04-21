# Premier League Application

This project is a REST API built with Spring Boot to serve Premier League data from a JSON file. It allows users to query Clubs by Goals, Wins, Losses, Position and check all Fixtures. This README provides a step-by-step guide to build, containerize, and deploy the application using Maven, Docker, and Kubernetes (with Kind for local development). Instructions are provided for both Windows and macOS/Linux users.

## Prerequisites

Before starting, ensure you have the following installed:

- **Java 17**: For building the Spring Boot application.
- **Maven**: For dependency management and building the JAR.
- **Docker**: For containerizing the application.
- **Kind**: For running a local Kubernetes cluster.
- **kubectl**: For interacting with Kubernetes.
- **curl** or **Postman**: For testing API endpoints.

### Installation (Windows)

For Windows, use **Chocolatey** (a package manager) or manual installation:

1. **Install Chocolatey** (optional, run in Admin PowerShell):
   ```powershell
   Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))
   ```

2. **Install Tools with Chocolatey**:
   ```powershell
   choco install openjdk17
   choco install maven
   choco install docker-desktop
   choco install kind
   choco install kubernetes-cli
   choco install curl
   ```

3. **Manual Installation**:
    - **Java 17**: Download from [Adoptium](https://adoptium.net/), set `JAVA_HOME` environment variable.
    - **Maven**: Download from [Apache Maven](https://maven.apache.org/download.cgi), add `bin` to `PATH`.
    - **Docker**: Install [Docker Desktop](https://www.docker.com/products/docker-desktop/), enable WSL 2 backend.
    - **Kind**: Download from [Kind releases](https://github.com/kubernetes-sigs/kind/releases), place in `C:\bin`.
    - **kubectl**: Download from [Kubernetes releases](https://kubernetes.io/docs/tasks/tools/install-kubectl-windows/), place in `C:\bin`.

4. **Set Up WSL 2** (recommended for Kind):
   ```powershell
   wsl --install
   wsl --update
   wsl --set-default-version 2
   ```

### Installation (macOS/Linux)
```bash
# Install Homebrew (macOS)
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# Install tools
brew install openjdk@17
brew install maven
brew install docker
brew install kind
brew install kubectl
```

## Project Structure

```
Pokedex-JSON/
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── actionSelector.java
│   │   │   ├── Club.java
│   │   │   ├── Fixture.java
│   │   │   ├── Main.java
│   │   │   ├── readClubs.java
│   │   │   ├── readFixtures.java
│   │   ├── resources/
│   │   │   ├── data.json
├── pom.xml
├── Dockerfile
├── premier-league-deployment.yaml
├── premier-league-service.yaml
├── README.md
```

- `data.json`: Contains Premier League data .
- `Dockerfile`: Defines the Docker image build process.
- Kubernetes manifests (`premier-league-deployment.yaml`, `premier-league-service.yaml`): Define the Kubernetes resources.

## Step 1: Build the JAR

The application is built using Maven to create an executable JAR file.

1. **Build the JAR**:

   ```bash
   mvn clean package -DskipTests
   ```

   - This compiles the code, runs the build, and generates `target/Premier-League-Application-1.0-SNAPSHOT.jar`.
   - The `-DskipTests` flag skips tests for faster builds (ensure tests pass if you have them).

2. **Run the Application Locally** (Optional):

   ```bash
   mvn spring-boot:run
   ```

   - This starts the Spring Boot application on `http://localhost:8080`.

3. **Test API Endpoints Locally**: Use `curl` or a browser to test the API:

   ```bash
   # Get all Clubs
   curl http://localhost:8080/api/clubs
   
   # Get Clubs Sorted by Goals
   curl http://localhost:8080/api/clubs/goals
   
   # Get Clubs Sorted by Losses
   curl http://localhost:8080/api/clubs/losses
   ```

   **Windows Alternative** (PowerShell):
   ```powershell
   Invoke-WebRequest -Uri http://localhost:8080/api/clubs
   ```

## Step 2: Containerize the Application with Docker

The application is packaged into a Docker container for deployment.

1. **Build the Docker Image**:

   ```bash
   docker build -t premier-league-app .
   ```

   - This creates a Docker image named `premier-league-app` based on the `Dockerfile`.
   
   - The `Dockerfile` uses a multi-stage build: Maven builds the JAR, and an Alpine JRE runs it.

2. **Test the Docker Container**:

   ```bash
   docker run -p 8080:8080 premier-league-app
   ```

   - Maps port `8080` on the host to `8080` in the container.
   
   - Test the API at `http://localhost:8080/api/clubs`.


3. **Push the Image to Docker Hub**: To make the image available to Kubernetes, push it to Docker Hub:

   ```bash
   # Log in to Docker Hub (create an account at https://hub.docker.com if needed)
   docker login
   
   # Tag the image with your Docker Hub username
   docker tag premier-league-app <your-dockerhub-username>/premier-league-app:latest
   
   # Push the image to Docker Hub
   docker push <your-dockerhub-username>/premier-league-app:latest
   ```

   - Replace `<your-dockerhub-username>` with your Docker Hub username.
   - This makes the image accessible to Kubernetes clusters, including Kind.

## Step 3: Deploy to Kubernetes with Kind

We use **Kind** to run a local Kubernetes cluster and deploy the API using Kubernetes manifests.

### 3.1 Set Up a Kind Cluster

1. **Create a Kind Cluster**:

   ```bash
   kind create cluster --name premier
   ```

   - This starts a local Kubernetes cluster named `premier`.

2. **Verify the Cluster**:

   ```bash
   kubectl cluster-info --context kind-premier
   ```

   - Ensures `kubectl` is connected to the Kind cluster.

### 3.2 Load the Docker Image into Kind (Optional)

If you prefer not to use Docker Hub, you can load the local `premier-league-app` image into Kind:

```bash
kind load docker-image premier-league-app:latest --name premier
```

- This makes the image available to the Kind cluster without needing a registry. Skip this step if you pushed the image to Docker Hub.

### 3.3 Deploy Kubernetes Resources

The application is deployed using a `Deployment` and exposed via a `Service`. Optionally, an `Ingress` can be used for HTTP access.

1. **Create the Deployment Manifest** (`premier-league-deployment.yaml`):

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: premier-league-app
  namespace: default
  labels:
    app: premier-league-app
spec:
  replicas: 2
  selector:
    matchLabels:
      app: premier-league-app
  template:
    metadata:
      labels:
        app: premier-league-app
    spec:
      containers:
        - name: premier-league-app
          image: <your-dockerhub-username>/premier-league-app:latest
          ports:
            - containerPort: 8080
          resources:
            limits:
              cpu: "500m"
              memory: "512Mi"
            requests:
              cpu: "200m"
              memory: "256Mi"
          livenessProbe:
            httpGet:
              path: /api/clubs
              port: 8080
            initialDelaySeconds: 15
            periodSeconds: 10
          readinessProbe:
            httpGet:
              path: /api/clubs
              port: 8080
            initialDelaySeconds: 5
            periodSeconds: 5
```      

- **Important**: Replace `<your-dockerhub-username>` with your Docker Hub username in the `image` field. If you used the local image with `kind load docker-image`, use `image: premier-league-app:latest` instead.

2. **Create the Service Manifest** (`premier-league-service.yaml`):

   ```yaml
   apiVersion: v1
   kind: Service
   metadata:
     name: premier-league-app-service
     namespace: default
   spec:
     selector:
       app: premier-league-app
     ports:
       - protocol: TCP
         port: 80
         targetPort: 8080
         nodePort: 30080
     type: NodePort
   ```

3. **Apply the Manifests**:

   ```bash
   kubectl apply -f premier-league-deployment.yaml
   kubectl apply -f premier-league-service.yaml
   ```

4. **Verify the Deployment**:

   ```bash
   kubectl get deployments
   kubectl get pods
   kubectl get services
   ```

   - Ensure the `premier-league-app` Deployment has 2/2 pods ready.
   - Check that `premier-league-app-service` is running with `type: NodePort`.

### 3.4 Access the API

The `NodePort` Service exposes the API on a high port (e.g., `30080`).

1. **Get the Cluster IP**:

   ```bash
   kubectl get nodes -o wide
   ```

   - Note the `INTERNAL-IP` of the Kind control plane node (e.g., `172.18.0.2`).

2. **Test the API**:

   ```bash
   curl http://<INTERNAL-IP>:30080/api/clubs
   curl http://<INTERNAL-IP>:30080/api/clubs/goals
   ```

   **Windows Alternative** (PowerShell):
   ```powershell
   Invoke-WebRequest -Uri http://<INTERNAL-IP>:30080/api/clubs
   ```

   - Replace `<INTERNAL-IP>` with the node’s IP.

3. **Port Forwarding** (Alternative):

   ```bash
   kubectl port-forward service/premier-league-app-service 8080:80
   ```

   - Access the API at `http://localhost:8080/api/clubs`.