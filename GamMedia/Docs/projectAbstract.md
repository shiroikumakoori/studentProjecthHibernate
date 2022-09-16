
# features 

## standard
- account creation 
- number of  views for each post
	- each click is 1 view.

### admin 
- delete and update any post 
- delete and update any users

### users 
- create post 
	- text
	- upload file
	- provide link to media
- update post created by own self 

### post
- The system should be able to track the number of views for each post

## good to have 
- support global emojis
	-  all user can use
- support custom emoji
	- belonging only to 1 user that only he can use

## super super good to have ( not likely to do )
- reading data from external api/app ( have no decided yet )


# technologies used 


## backend 
spring boot 2.7.3
## frontend 
angular  14

## database
mysql

# project models 

### admin
- id 
- userName
- password
 

### user
- id 
- userName
- password
- name
- email 
- 

### post ( created by user)
- id 
- title
- message
- foreign id (user)



