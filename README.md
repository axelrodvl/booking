# The most common kubectl commands

- Install kubectl

    
    curl -LO https://dl.k8s.io/release/curl -LS https://dl.k8s.io/release/stable.txt`/bin/linux/amd64/kubectl

- Makes the downloaded kubectl file executable


    chmod +x ./kubectl

- Moves the kubectl file to the /usr/local/bin system directory so that it can be accessed from anywhere on the system.


    sudo mv ./kubectl /usr/local/bin/kubectl

- Check kubectl version


    kubectl version --client

- Copy kubeconfig file


    cp booking-k8s-kubeconfig.yaml ~
    kubectl --kubeconfig=booking-k8s-kubeconfig.yaml get nodes
    cp booking-k8s-kubeconfig.yaml .kube/
    cp booking-k8s-kubeconfig.yaml config

- Install Helm


    sudo apt install helm

- Installs a Helm-chart named "my-release" from the specified Docker Hub repository


    helm install my-release oci://registry-1.docker.io/bitnamicharts/postgresql

- Outputs a list of pods in a Kubernetes cluster


    kubectl get pods

- Removes the installed Helm-chart named "my-release"


    helm delete my-release

- Sets port forwarding to access the PostgreSQL service through local port 5432


    kubectl port-forward --namespace default svc/postgresql 5432:5432 &

- Check the availability of resources


    kubectl describe pod postgresql-0

- Checks the available nodes in your Kubernetes cluster


    kubectl get nodes

- Checks for PVC-related errors or warnings


    kubectl describe pvc postgresql-pv-claim

- More information about the state of the PostgreSQL feed


    kubectl describe pod postgresql-0

- Get information about all nodes in your Kubernetes cluster

    
    kubectl get nodes -o wide

- Getting more detailed information about the status of nodes

    
    kubectl describe nodes

- Install PostgreSQL client


    sudo apt install postgresql-client-common
        


# Connecting to PostgreSQL using Port-Forwarding

This guide provides step-by-step instructions on how to connect to PostgreSQL using port-forwarding.
Port-forwarding allows you to access a PostgreSQL database running on a remote server from your local machine.

## Steps

1. Open a terminal or command prompt on your local machine.

2. Set up port-forwarding to forward traffic from a local port to the PostgreSQL database port on the remote server.
   Use the following command:

    kubectl port-forward --namespace default svc/postgresql 5432:5432 &

3. Open your PostgreSQL client (e.g., DBeaver) on your local machine.

4. Configure the PostgreSQL client connection with the following details:

    Host: localhost or 127.0.0.1
    Port: 5432
    Database: "booking"
    User: "postgres"
    Password: "postgres"

5. Click "Connect" or a similar button to establish the connection to the PostgreSQL database.

6. You should now be connected to the PostgreSQL database running on the remote server through port-forwarding.