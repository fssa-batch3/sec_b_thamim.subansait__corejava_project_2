# DOBOO Application Checklist

## Database Design

- [x] Create an ER diagram of the database- [ ![\] ](https://iili.io/HZmKMNI.png)Write Create table scripts [script](path/to/sql/file)

## Project Setup

- [ ] Create a new Java project
- [ ] Set up a MySQL database
- [ ] Add necessary libraries
	- [ ] JDBC, 
	- [ ] MySQL Connector, 
	- [ ] JUnit, 
	- [ ] Dotenv

## Module: User  

### Feature 1 : create User
user Story 
	
		 user details will successfully store in database 
### Pre - requisites : -

- [ ] user table
- [ ] user model
- [ ] user Dao(create) 
- [ ] user service (create)

#### Validations:

- [ ] Form validator 
	 * user object (null)
	 * user email (null, empty, pattern)
	 * user name (null, empty, pattern)
	 * user password(null, empty, pattern)
	 * user date of birth( null, empty,age greater than 16)

- [ ] Business Validation  
	* Email Already exists

#### Messages :  

* User object cannot be null  
* Invalid name input  
* Invalid email input  
* Invalid password input  
* age must be 16   
* User already exists
 
#### Flow:  
> Invalid When a user's email is already in use or when the input provided does not meet the criteria, users arise.

```mermaid  

graph TD;  

A(UserService - Object user) --> B(Form Validation) -- Valid --> C(Business Validation)  

B -- Invalid --> H(Validation Exception) 

C -- Valid --> D(Argument Passed to UserDAO)  

D --> F(Store Value in Database)  

C -- Invalid --> G(Validation Exception)

```

### Feature 2 : Update User  
user Story
	
		user details will successfully update and store in database
				
### Pre-requisites:-

- [ ] completed feature 1 - create user

- [ ] user dao (update)

- [ ] user service (update) 

#### Validations:  

​

- [ ] Form validator  

	*  id (less than or equal to 0) 

	* name ( null, empty, pattern)  

	* password (null , empty, pattern)
	
	*  artist_name(null , empty)


- [ ] Business Validation  

	* Check whether the id exist

​

#### Messages :  

* Invalid id  

*  Invalid name input

* Invalid password input  

* User not found  

​

#### Flow:  

> Invalid When a user's  email is not in use or when the input provided does not meet the criteria, users arise.

​

```mermaid  

graph TD;  


A(UserService - int id, String name) --> B(Form Validation) -- Valid --> C(Business Validation) 

B -- Invalid --> H(Validation Exception) 

C -- Valid --> D(Argument Passed to UserDAO)  

D --> F(Store Value in Database)  

C -- Invalid --> E(Validation Exception)
```

### Feature 3 : Delete User  
user Story
	
		 user details will successfully delete
​

### Pre-requisites:- 


- [ ] Completed feature 1 - create user

- [ ] user dao (delete)

- [ ] user service (delete)

​

#### Validations:  


- [ ]  Form Validation 

	* id (less than or equal to 0)​

- [ ] Business Validation  

	* Check whether the id exist



#### Messages :  

* Invalid id  

* User not found 

​

#### Flow:  

> Invalid When a user's id is not in use or when the input provided does not meet the criteria, users arise.

​

```mermaid  

graph TD;  


A(Argument Passed to UserService - int id) --> B(Form Validation) -- Valid --> C(Business Validation)  

B -- Invalid --> H(Validation Ecxeption)

C -- Valid --> D(Argument Passed to UserDAO)  

D --> F(change is_active Value in Database)  

C -- Invalid --> G(Validation Exception)
```
## Module: Track 


### Feature 4 :  uploading Track

user Story
	
		 user will successfully upload track

### Pre-requisites:- 

- [ ] Completed feature 1 - create user
- [ ] track table
- [ ] track model
- [ ] track dao (create)
- [ ] track service(create)
- [ ] track price table
- [ ] track price model
- [ ] track price dao(create)
- [ ] track price service(create)
- [ ] asset table
- [ ] asset model
- [ ] asset dao(create)
- [ ] asset service (create)


#### Validations:

- [ ] Form validator 
	 * track object (null)
	 * track name (null, empty,pattern)
	 * track price(null,empty,0)
	 * asset audio(ext,url)
	 * asset image(ext,url)
#### Messages :  
   * audio/image invalid format type
   * price must be not 0 or not null
   * track name is empty or null 

#### Flow:  

> Invalid When a track details or image / audio format and name is null or when the input provided does not meet the criteria, users arise.
​
```mermaid  

graph TD;  

A(track service argument track object)-->B(Form Valiation)--valid-->C(Business Validation)
B--Invalid-->D(Validation Exception)
C--valid-->E(Track dao)
C--Invalid-->F(validation Exception)
E-->I(result)-->asset_service-->G(Form validation)
G--valid-->H(asset Dao)
G--Invalid-->J(validation Exception)
H-->O(result)
O-->Q(track price service)
Q-->P(Form validation)
P--valid-->S(track price dao)
P--Invalid-->T(validation exception)
S-->V(Stored in database)
```
### Feature 5 :  update Track details
user Story
	
		 user will successfully edit the tracks details
		 
### Pre-requisites:- 

- [ ] Completed feature 1 - create user
- [ ] completed feature 4 - upload track
- [ ] track model
- [ ] track dao(update)
- [ ] track service(update)

#### Validations:

- [ ] Form validator 
	 * track object (null)
	 * track id (greater than 0)
	 * track name (null, empty, pattern)
- [ ] Business Validation  

	* Check whether the id exist

#### Message:  
   * track name is empty or null 

#### Flow:  

> Invalid When a track details or name is null or when the input provided does not meet the criteria, users arise.

​

```mermaid  

graph TD;  


A(Argument Passed to TrackService - track object) --> B(Form Validation) -- Valid -->E(Business Validation)
B--Invalid-->F(Validation exception)
E--valid-->G(Track dao)
E--Invalid-->H(validation Exception)
G-->I(Stored in database)

```
### Feature 6 :  delete Track
user Story
	
		 user will successfully delete the tracks
		 
### Pre-requisites:- 

- [ ] Completed feature 1 - create user
- [ ] completed feature 4 - upload track
- [ ] track model
- [ ] track dao (delete)
- [ ] track service(delete)


#### Validations:

- [ ] Form validator 
	 * id (greater than 0)
- [ ] Business Validation  

	* Check whether the id exist

#### Exceptions:  
   * Invalid id 

#### Flow:  

> Invalid When a track details or image / audio format and name is null or when the input provided does not meet the criteria, users arise.

​

```mermaid  

graph TD;  


A(track service argument track id)-->B(Form validation)--valid-->D(Business Validation)
B--Invalid-->C(validation Exception)
D--valid-->E(track dao)
D--Invalid-->F(validation Exception)
E-->G(change the is_active to false)


```

### Feature 7: Find All Tracks

user Story
	
		 user will successfully view all tracks

#### Pre-requisites:

- [ ] Completed feature 1 - create user
- [ ] track table
- [ ] track model
- [ ] track dao 
- [ ] track service
- [ ] track price table
- [ ] track price model
- [ ] track price dao
- [ ] track price service
- [ ] asset table
- [ ] asset model
- [ ] asset dao
- [ ] asset service 

#### Validations:

#### Flow:

```mermaid

graph TD;

  A[track service] --> B[track dao]-->C(result of all tracks)-->E(asset service)-->F(asset dao)-->G(result of all tracks asset)-->H(track price service)-->I(track price Dao)-->J(result of all tracks price)
```
### Feature 8: Find All Tracks By Artist Name

user Story
	
		 user will successfully view all tracks by using artist name 

#### Pre-requisites:

- [ ] Completed feature 1 - create user
- [ ] track table
- [ ] track model
- [ ] track dao 
- [ ] track service
- [ ] track price table
- [ ] track price model
- [ ] track price dao
- [ ] track price service
- [ ] asset table
- [ ] asset model
- [ ] asset dao
- [ ] asset service 

#### Validations:

- [ ] Form validator 
	 * artist name(null,empty)
- [ ] Business Validation  

	* Check whether the artist exist

#### Flow:

```mermaid

graph TD;

  A[track service argument artist name]-->X(Form validation) 
 
  X--valid--> Z(Business Validation)
  Z--valid-->B[track dao]-->C(result of all tracks)-->E(asset service)-->F(asset dao)-->G(result of all tracks asset)-->H(track price service)-->I(track price Dao)-->J(result of all tracks price)
   X--Invalid-->Y(validation exception)
   Z--Invalid-->V(validation exception)
```
### Feature 9: Find Matching Tracks By Track Name

user Story
	
		 user will successfully view all tracks by using track name 

#### Pre-requisites:

- [ ] Completed feature 1 - create user
- [ ] track table
- [ ] track model
- [ ] track dao 
- [ ] track service
- [ ] track price table
- [ ] track price model
- [ ] track price dao
- [ ] track price service
- [ ] asset table
- [ ] asset model
- [ ] asset dao
- [ ] asset service 

#### Validations:

- [ ] Form validator 
	 * track name(null,empty)
- [ ] Business Validation  

	* Check whether the track exist

#### Flow:

```mermaid

graph TD;

  A[track service argument Track name]-->X(Form validation) 
 
  X--valid--> Z(Business Validation)
  Z--valid-->B[track dao]-->C(result of all tracks)-->E(asset service)-->F(asset dao)-->G(result of all tracks asset)-->H(track price service)-->I(track price Dao)-->J(result of all tracks price)
   X--Invalid-->Y(validation exception)
   Z--Invalid-->V(validation exception)
```






