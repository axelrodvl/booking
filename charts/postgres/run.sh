kubectl apply -f postgres-pv.yaml
kubectl apply -f postgres-pvc.yaml
helm install postgresql -f values.yaml bitnami/postgresql
