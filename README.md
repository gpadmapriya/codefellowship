#### App CodeFellowship
- Allows people to connect with and support each other on their coding journeys

#### Feature Tasks
- The site should have a splash page at the root route that contains a link to the sign up page
- User information on the site should include username, password, firstname, lastname, dateofbirth and bio
- Site should allow for users to signup.
- Site should allow for viewing data about a single user
- Site should allow for a user to login. Login and user registration should take the user to their profile page.
- Homepage, login and registration pages should be accessible to non-logged in users.
- Other routes like myprofile should be limited to logged in users.
- Add a Post entity to the app which has a body and a created at timestamp. A logged in user should be able to create a post and that post should belong to that user.
- A user's posts should be visible on their profile page.
- When a user is logged in, the app should display the user's name on every page.
- The site should use at least one reusable template.
- The site should have a non white label error handling page.
- At a minimum, test for the functionality of the root route and signup page

#### Available Routes
  - / - root route that has links to login or signup
  - /login - login page 
  - /signup - Create new user
  - /users/{id} - view specific user information
  - /myprofile - signed in users can view their profile
  - /post/create - signed in users can create new posts

#### To Run
- Spring MVC should be running
- Create a database in PostgreSQL called codefellowship
- Update application.properties to include
  - spring.datasource.url=jdbc:postgresql://localhost:5432/albums
  - spring.datasource.username=username (postgres user name)
  - spring.datasource.password=pwd (postgres password)
  - server.error.include-stacktrace=always
  - spring.jpa.hibernate.ddl-auto=create
  - To persist data between requests, comment out the previous line (use # to comment out) in  application.properties after running for the first time
- Run CodefellowshipApplication in IntelliJ
- Routes should be available when trying from localhost at port 8080 (http://localhost/8080)
- Use gradle bootRun to run from terminal

#### Resources
[Spring Auth cheat sheet](https://github.com/codefellows/seattle-java-401d5/blob/master/SpringAuthCheatSheet.md)