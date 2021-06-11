# Demantic_Library

Description Library service provides Api's for for quick book storage, update, access and total price calculation

Ownership This project owned by Kaviya Team (Email - kavythangaraj@gmail.com)

## Tech Stack

Spring Boot H2 DB //can be replaced with any other DB's

Quick Start Clone the git repository

docker build --tag=library

docker run -p8080:8080 library

Application starts in 8080 port Application

starts in 8080 port

## API's exposed

http://localhost:8080/book/add

sample Input:

{
"books": [
{
"name": "Story",
"barcode":"78999",
"author":"kavy",
"quantity":1,
"price":10.00,
"scienceIndex": 8 }
]
}

CalculateTotalAPI:

http://localhost:8080/book/calculatetotal/{barCode}

UpdateDetailsAPI:

http://localhost:8080/book/{barCode}

sampleInput:
{
"books": [
{
"scienceIndex": 5,
"bookId": 1 }
]
}

GetBooksByBarCode:

http://localhost:8080/book/{barCode}