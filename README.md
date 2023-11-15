
# MINI DEMO

A mini spring application to notify employee's managers of their leave request.




## Installation

Running with gradle and docker compose

```bash
  ./gradlew bootJar
  docker compose build --build-arg JAR="mini-employee-service-0.0.1.jar"
  docker compose up -d 

```


    
## Demo API

- Heres the list of employees inside the database

```sql
INSERT INTO employee (id, name, position, email, manager_id)
VALUES
('1','Tri','MNGR0','tri@mail.com', null),
('2','Rahman','MNGR1','rahman@mail.com', '1'),
('3','Ilham','MNGR1','ilham@mail.com', '1'),
('4','Arif','MNGR2','arif@mail.com', '2'),
('5','Hasan','MNGR2','hasan@mail.com', '2'),
('6','Sulaiman','STAFF','sulaiman@mail.com', '4'),
('7','Citra','STAFF','citra@mail.com', '4'),
('8','Taufik','STAFF','taufik@mail.com', '5'),
('9','Yuda','STAFF','yuda@mail.com', '5'),
('10','Bagus','STAFF','bagus@mail.com', '5');
```

- Ex. If you want to find all of Sulaiman's manager

```bash
curl --request GET \
  --url 'http://localhost:5000/api/employee/find-all-managers?id=6'
```

- The result should be
```json
[
	{
		"email": "arif@mail.com",
		"name": "Arif",
		"position": "MNGR2"
	},
	{
		"email": "rahman@mail.com",
		"name": "Rahman",
		"position": "MNGR1"
	},
	{
		"email": "tri@mail.com",
		"name": "Tri",
		"position": "MNGR0"
	}
]
```

- Ex. if you want to make a leave request on behalf of Sulaiman

```bash
curl --request POST \
  --url http://localhost:5000/api/employee-on-leave \
  --header 'Content-Type: application/json' \
  --data '{
	"employeeId": "6",
	"startDate": 1699997100,
	"endDate": 1700169900
}'
```

- The result should just be a http response with status **200**

- Ex. if you want to see all of Sulaiman's leave requests recently

```bash
curl --request GET \
  --url http://localhost:5000/api/employee-on-leave/requested-by/6
```

- The result should be just like

```json
[
	{
		"id": "519aa090-82fc-4135-8245-c421ed9bb255",
		"startDate": "2023-11-14T21:25:00Z",
		"endDate": "2023-11-16T21:25:00Z",
		"employee": {
			"id": "6",
			"name": "Sulaiman",
			"email": "sulaiman@mail.com",
			"position": "STAFF"
		},
		"requestDate": "2023-11-15T04:26:13.896434Z"
	}
]
```

- Ex. if you want to notify all of Sulaiman's manager of the leave request created before

```bash
curl --request GET \
  --url http://localhost:5000/api/employee-on-leave/notify-managers/519aa090-82fc-4135-8245-c421ed9bb255
```

- The result should be

```json
{
	"subject": "[arif@mail.com, rahman@mail.com, tri@mail.com]",
	"message": "Sulaiman has requested leave from 2023-11-15 until 2023-11-17",
	"messageName": "LEAVE"
}
```
