docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres











sudo docker run --name booking-postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=booking -p 5432:5432 -d postgres