**Monolithic Java Quiz Application**

**Overview**
The Monolithic Java Quiz App is a dynamic and fully functional quiz management system designed to create, manage, and evaluate quizzes. Developed using Spring Boot and Hibernate, this application allows users to take quizzes in a specified category, records their responses, and calculates scores upon submission. The monolithic architecture enables seamless integration of all components within a single application.

**Features**
Quiz Creation: Create a quiz by selecting questions from a specific category.
Dynamic Question Selection: Select a random set of questions for each quiz.
Quiz Attempt: Retrieve and answer quiz questions through RESTful APIs.
Automatic Scoring: Evaluate user responses and calculate the score in real-time.
Entity Relationships: Uses @ManyToMany relationships between quizzes and questions.
Order Preservation: Maintains the order of questions using a List<Question> structure.
Error Handling: Provides detailed error messages for invalid quiz or question IDs.

**Tech Stack**
Backend: Spring Boot, Hibernate/JPA
Database: MySQL (or other relational databases)
Java Version: Java 17+
Build Tool: Maven

**Application Architecture**
This application uses a monolithic architecture, where all features are tightly coupled into a single deployment unit. Its core functionality includes:
Data Layer: Manages data persistence with Hibernate ORM.
Service Layer: Contains business logic for quiz creation, question retrieval, and scoring.
Controller Layer: Exposes REST endpoints for client interactions.
**Getting Started**
Prerequisites
Install Java 17 or higher.

Set up a MySQL database and update the connection details in application.properties:

properties
Copy code
__spring.datasource.url=jdbc:mysql://localhost:3306/quizdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
Install Maven for dependency management.__

**Running the Application**
Clone the repository:

Copy code
git clone https://github.com/your-username/Monolithic_Java_Quiz_App.git
cd Monolithic_Java_Quiz_App
Build and run the application.

Access the application API at http://localhost:8080.

**API Endpoints**
Method	Endpoint	Description
POST	/api/quiz/create	Create a new quiz by specifying category, number of questions, and title.
GET	/api/quiz/getQuiz/{id}	Retrieve questions for a specific quiz ID.
POST	/api/quiz/submit/{id}	Submit quiz answers and calculate the score.
Sample Request: Create Quiz
Copy code
POST /api/quiz/create?category=Java&numQ=5&quizTitle=Java Basics Quiz
Sample Request: Submit Quiz
Copy code
POST /api/quiz/submit/1
Content-Type: application/json

[
  { "questionId": 1, "response": "Option1" },
  { "questionId": 2, "response": "Option3" }
]
Response:
json
{
  "message": "Your Score: 2"
}
Database Schema
Quiz: Stores quiz metadata (e.g., title).
Question: Contains questions, options, and the correct answer.
quiz_question: A join table mapping quizzes to questions.

**Future Enhancements**
Implement a frontend using Angular/React for an enhanced user experience.
Add user authentication and quiz history tracking.
Enable cloud-based deployment with Docker and Kubernetes.
Expand support for more complex quiz types (e.g., timed quizzes).
Contribution
We welcome contributions! Feel free to fork the repository, make changes, and submit a pull request.

Author: Madhumitha Koratagere Ramesh
