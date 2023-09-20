# The most common kubectl commands

My new record

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

# Booking service from scratch
## Technologies
- Backend: Java 17, Spring 6, Spring Boot 3, Feign
- Frontend: TypeScript, React, Antd
- Deployment: Jenkins, Docker, Kubernetes
- Monitoring and logging: ELK, Grafana
- Queuing: Kafka
- Data storage: PostgreSQL
- Authorization: OAuth 2.0 and JWT
- Users management: OpenLDAP (?)
- Caching: Redis
## Services
### FE
- guest-fe
- host-fe
- admin-fe
### BE
- property-service
  - Creating and deleting property
  - Property management
    - Price
    - Availability
    - Description
    - Policy
- payment-service
  - chargeCard
  - refundTransaction
    - Receive money
- booking-service
  - Book property
  - Cancel booking and refund
- search-service
  - Search for property
- guest-service
  - Book property
  - Payments
- host-service
  - Manage property
  - Payments
- admin-service
  - Read-only access to all bookings
  - Initaite refund
- messaging-service
  - Send messages between guest, host and support
- mail-service
  - Send E-Mails to guest and host
- sms-service
  - Send SMS to guest and host
### Deployment
- Kubernetes
- Jenkins
- ELK
- Grafana
- Kafka (cluster, 3 nodes)
- PostgreSQL
- Redis (cluster, 3 nodes)
- OAuth Server
# Entites
- guest
  - name
  - phone
  - email
  - cards
- host
  - name: string
  - cards
  - properties: ref:property
- admin
- property
- payment
  - transaction
    - payment
    - refund
  - card
    - number
    - validUntil
    - cvv
# Processes

User Story
As <someone>
I want <something>
To do <someting>

Как администратор
Я хочу доступ на чтение бронирований клиента
Чтобы просматривать бронирования и отменять их при необходи

## Booking
1. fe-client -> booking-service
  1. BookingRequest
    - property
    - dateFrom
    - dateTo
    - cardId
1. booking-service
  - property-service.isAvailable(propertyId)
  - guest-service.getGuest(token.guestId)
  - property-service.setUnavailable(property, dateFrom, dateTo)
  - guest-service.addBooking(property, dateFrom, dateTo, totalPrice, BookingStatus.RESERVED)
  - payment-service.chargeCard(cardId, amount)
  - guest-service.setBookingStatus(booking, BookingStatus.PAID)
