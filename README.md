# Kubernetes Patters

## 1. Sidecar Pattern

### Prepare docker image for kuard app
1. Build the docker image: `docker build -t <PRIVATE_REGISTRY_NAME>.azurecr.io/kuar-demo/kuard:blue-amd64 --platform linux/amd64 .`
2. Push the docker image to private registry: `docker push <PRIVATE_REGISTRY_NAME>.azurecr.io/kuar-demo/kuard:blue-amd64`

### Deploy kuard app on azure
1. Create configmap: `kubectl create configmap kuard-config --from-env-file=kuard-config`
2. Deploy the app: `kubectl apply -f kuard-deployment.yaml`

## 2. Ambassador Pattern

### Prepare docker image for ambassador app
1. Build the docker image: `docker build -t <PRIVATE_REGISTRY_NAME>.azurecr.io/ambassador-demo/springredisproxy:amd64 --platform linux/amd64 .`
2. Push the docker image to private registry: `docker push <PRIVATE_REGISTRY_NAME>.azurecr.io/ambassador-demo/springredisproxy:amd64`

### Deploy ambassador app on azure
1. Create configmap: `kubectl create configmap twem-config --from-file=./nutcracker.yaml`
2. Deploy the app: `kubectl apply -f ambassador-deployment.yaml`
3. Create `redis` service with clusterIP as `none`: `kubectl apply -f redis-service.yaml`
4. Create statefulset for redis: `kubectl apply -f redis-statefulset.yaml`
5. Deploy the app: `kubectl apply -f ambassador-deployment.yaml`


## Login to Azure
1. Login using az cli: `az login`
2. Merge the credentials with kubectl: `az aks get-credentials --resource-group=<RESOURCE_GROUP> --name=<AKS_CLUSTER_NAME>`
3. Login to private registry: `az acr login --name <PRIVATE_REGISTRY_NAME>.azurecr.io`
4. Create secret to access acr: `kubectl create secret docker-registry dhrsharm-cr-secret --docker-server=<PRIVATE_REGISTRY_NAME>.azurecr.io --docker-username=<USERNAME> --docker-password=<TOKEN> --docker-email=<EMAIL>`

## Debugging commands:
1. Check logs of pod: `kubectl logs <POD_NAME> --all-containers=true`
2. Port forward: `kubectl port-forward <POD_NAME> 48858:3000`