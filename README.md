# REST API for TApp
## Authentication
In progress...
## Methods
### Users
Get on `v1/users` will return a list of all users \
Put on `v1/users/{id}` where id - vk id will create entity in database \
Get on `v1/users/{id}` will return a single user with this id \
Patch on `v1/users/{id}/add_test/{test_id}`  will 
add test with id `test_id` to user with `id` \
Patch on `v1/users/{id}` will update user with `id` \
Delete on `v1/users/{id}` will delete user

### Tests
Get on `v1/tests` will return a list of all tests \
Get on `v1/tests/{id}` will will return a single user with this `id` \
Post on `v1/tests` will create a new entity of test in database and return its id \
Delete on `v1/tests/{id}` will delete test with id = `id`

### Images
Get on `v1/media/{name}` will return image with this name (e.g. "image.png")
Put on `v1/media/{name}` will save this image by the name of `name`
