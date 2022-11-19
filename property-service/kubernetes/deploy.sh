kubectl config use-context do-fra1-k8s-do-fra1
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
kubectl apply -f application.yaml