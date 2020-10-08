# REST API for TApp
## Authorization
The API uses [vk params sign check](https://vk.com/dev/vk_apps_docs3?f=6.1%2BПодпись%2Bпараметров%2Bзапуска). The param string is passed with header "params".
The string must not contain domains (e.g. https://example.com/).
 
 Example for string `?vk_access_token_settings=notify&vk_app_id=6736218&vk_are_notifications_enabled=0&vk_is_app_user=0&vk_language=ru&vk_platform=android&vk_user_id=494075`

## Methods
-> Right arrow - `request` JSON body \
<- Left arrow - `response` JSON body

### Users
`Get` on `v1/users` returns a list of all users \
<-
```json
[
    {
        "id": 0,
        "score": 10,
        "school": null,
        "bdate": "1.10",
        "role": "user",
        "activeSticker": null,
        "stickers": []
    },
    {
        "id": 137239419,
        "score": 97,
        "school": null,
        "bdate": "1.10",
        "role": "admin",
        "activeSticker": {
            "id": 1,
            "cost": 30,
            "img": "https://tapp-media.s3.eu-north-1.amazonaws.com/sticker_images/1.png",
            "name": "Дэвид Гилберт",
            "description": "",
            "quote": "Он стал поэтом. Для математики у него было слишком мало воображения"
        },
        "stickers": [
            {
                "id": 3,
                "cost": 3,
                "img": "https://tapp-media.s3.eu-north-1.amazonaws.com/sticker_images/2.png",
                "name": "SOMEBODY",
                "description": "NOTHING HERE",
                "quote": "COOL QUoTE"
            },
            {
                "id": 1,
                "cost": 30,
                "img": "https://tapp-media.s3.eu-north-1.amazonaws.com/sticker_images/1.png",
                "name": "Дэвид Гилберт",
                "description": "",
                "quote": "Он стал поэтом. Для математики у него было слишком мало воображения"
            }
        ]
    }
]
``` 
\
\
`Put` on `v1/users/{id}` where id - vk id will, creates an entity in database and return this entity \
Only `id` is required 

-> 
```json
{
    "score": 10,
    "school": "",
    "age": 16,
    "role": "user"
}
```
\
\
`Get` on `v1/users/{id}` returns a single user with this id 
```json
{
    "id": 137239419,
    "score": 97,
    "school": null,
    "bdate": "1.10",
    "role": "admin",
    "activeSticker": {
        "id": 1,
        "cost": 30,
        "img": "https://tapp-media.s3.eu-north-1.amazonaws.com/sticker_images/1.png",
        "name": "Дэвид Гилберт",
        "description": "",
        "quote": "Он стал поэтом. Для математики у него было слишком мало воображения"
    },
    "stickers": [
        {
            "id": 3,
            "cost": 3,
            "img": "https://tapp-media.s3.eu-north-1.amazonaws.com/sticker_images/2.png",
            "name": "SOMEBODY",
            "description": "NOTHING HERE",
            "quote": "COOL QUoTE"
        },
        {
            "id": 1,
            "cost": 30,
            "img": "https://tapp-media.s3.eu-north-1.amazonaws.com/sticker_images/1.png",
            "name": "Дэвид Гилберт",
            "description": "",
            "quote": "Он стал поэтом. Для математики у него было слишком мало воображения"
        }
    ]
}
```
\
\
`GET` on `v1/users/{userId}/get_history/{testId}` returns all history for user with id = `{userId}` and test with id = `{testId}`   \
-> 
```json
[
    {
        "id": 1060,
        "question": {...},
        "date": "19-09-2020 21:04:18",
        "eventCode": 0,
        "score": 0
    },
    {
        "id": 1061,
        "question": {...},
        "date": "19-09-2020 21:04:28",
        "eventCode": 2,
        "score": 1
    },
    {
        "id": 1062,
        "question": {
            "id": 91,
            "questionText": "В каком случае относительная скорость движущихся автомобилей максимальна?",
            "reward": 1,
            "img": null,
            "answers": [
                {
                    "id": 358,
                    "answerType": "text",
                    "answer": "Векторы их скоростей составляют острый угол",
                    "isRight": 0
                },
                {
                    "id": 357,
                    "answerType": "text",
                    "answer": "Догоняют друг друга",
                    "isRight": 0
                },
                {
                    "id": 360,
                    "answerType": "text",
                    "answer": "Автомобили движутся навстречу друг друг",
                    "isRight": 1
                },
                {
                    "id": 359,
                    "answerType": "text",
                    "answer": "Едут в разных направлениях",
                    "isRight": 0
                }
            ],
            "explain": null,
            "serialNumber": 1
        },
        "date": "19-09-2020 21:04:29",
        "eventCode": 0,
        "score": 1
    },
   
]
```
\
\
`PATCH` on `v1/users/{userId}/buy_sticker/{stickerId}` buys sticker with id = `{stickerId}`
for user with id = `{userId}`. \
Return patched user entity (see `v1/users/{id}` ) 
\
\
`PATCH` on `v1/users/{userId}/set_active_sticker/{stickerId}` sets a sticker with id = `{stickerId}` active 
for user with id = `{userId}`. \
Return patched user entity (see `v1/users/{id}` ) 

### Tests
`Get` on `v1/tests` returns a list of all tests \
<-
```json
[
    {
        "id": 10,
        "img": "https://tapp-media.s3.eu-north-1.amazonaws.com/test_images/abstract-technology-particle-background_52683-25766.png",
        "title": "Приключение на 20 минут. Погнали?",
        "description": "Дальше пройдет лишь достойный! Проверь себя! А если победишь, то получишь ценную награду.",
        "questions": [
            {
                "id": 90,
                "questionText": "Чему равен период минутной стрелки?",
                "reward": 1,
                "img": null,
                "answers": [
                    {
                        "id": 355,
                        "answerType": "text",
                        "answer": "12 часов",
                        "isRight": 0
                    },
                    {
                        "id": 353,
                        "answerType": "text",
                        "answer": "3600 секунд",
                        "isRight": 1
                    },
                    {
                        "id": 356,
                        "answerType": "text",
                        "answer": "24 часа",
                        "isRight": 0
                    },
                    {
                        "id": 354,
                        "answerType": "text",
                        "answer": "1 секунда",
                        "isRight": 0
                    }
                ],
                "explain": null,
                "serialNumber": 2
            },
            ... 
        ],
        "date": "22-07-2020 00:00:00",
        "maxScore": 5,
        "timeToComplete": null
    },
    {
        "id": 11,
        "img": "https://tapp-media.s3.eu-north-1.amazonaws.com/test_images/photo-1546146477-15a587cd3fcb.png",
        "title": "Как приручить интеграл Римана?",
        "description": "Дальше пройдет лишь достойный! Проверь себя! А если победишь, то получишь ценную награду.",
        "questions": [...],
        "date": "21-07-2020 00:00:00",
        "maxScore": 11,
        "timeToComplete": null
    }
]
```
\
\
`Get` on `v1/tests/{id}` returns a single test with id = `{id}` \
<-
```json
    {
        "id": 10,
        "img": "https://tapp-media.s3.eu-north-1.amazonaws.com/test_images/abstract-technology-particle-background_52683-25766.png",
        "title": "Приключение на 20 минут. Погнали?",
        "description": "Дальше пройдет лишь достойный! Проверь себя! А если победишь, то получишь ценную награду.",
        "questions": [
            {
                "id": 90,
                "questionText": "Чему равен период минутной стрелки?",
                "reward": 1,
                "img": null,
                "answers": [
                    {
                        "id": 355,
                        "answerType": "text",
                        "answer": "12 часов",
                        "isRight": 0
                    },
                    {
                        "id": 353,
                        "answerType": "text",
                        "answer": "3600 секунд",
                        "isRight": 1
                    },
                    {
                        "id": 356,
                        "answerType": "text",
                        "answer": "24 часа",
                        "isRight": 0
                    },
                    {
                        "id": 354,
                        "answerType": "text",
                        "answer": "1 секунда",
                        "isRight": 0
                    }
                ],
                "explain": null,
                "serialNumber": 2
            },
            ... 
        ],
        "date": "22-07-2020 00:00:00",
        "maxScore": 5,
        "timeToComplete": null
    }
```
\
\
`Post` on `v1/tests` creates a new entity of test in database and return this entity \
Attention date format: `dd-MM-yyyy HH:mm:ss`

-> 
```json
{
    "img": "https://media.gettyimages.com/photos/abstract-network-background-picture-id836272842?s=612x612",
    "title": "Как приручить интеграл Римана?",
    "description": "descidghd",
    "questions": [
        {
            "questionText": "nothing",
            "pathToImage": "None",
            "answers": [],
            "serialNumber": 0
        }
    ],
    "date": "01-10-2020 00:01:01"
}
```
Delete on `v1/tests/{id}` will delete test with id = `id` \
`*NOTHING*`

### Images
`POST` on `v1/media` will upload an attached image to Amazon S3 cloud storage and return url to get it. 
Images must have `img` key.\
<-
```json
{
  "url": "https://tapp-media.s3.eu-north-1.amazonaws.com/test_images/a.jpg" 
}
```
