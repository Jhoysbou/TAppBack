# REST API for TApp
## Authorization
The API uses [vk params sign check](https://vk.com/dev/vk_apps_docs3?f=6.1%2BПодпись%2Bпараметров%2Bзапуска). The param string is passed with header "params".
The string must not contain domains (e.g. https://example.com/).
 
 Example for string `?vk_access_token_settings=notify&vk_app_id=6736218&vk_are_notifications_enabled=0&vk_is_app_user=0&vk_language=ru&vk_platform=android&vk_user_id=494075`

---
title: Api Documentation v1.0
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
highlight_theme: darkula
headingLevel: 2

---

<!-- Generator: Widdershins v4.0.1 -->

<h1 id="api-documentation">Api Documentation v1.0</h1>

> Scroll down for code samples, example requests and responses. Select a language for code samples from the tabs above or the mobile navigation menu.

Api Documentation

Base URLs:

* <a href="//localhost/">//localhost/</a>

<a href="urn:tos">Terms of service</a>

License: <a href="http://www.apache.org/licenses/LICENSE-2.0">Apache 2.0</a>

<h1 id="api-documentation-test-controller">test-controller</h1>

Test Controller

## getAllTestsUsingGET

<a id="opIdgetAllTestsUsingGET"></a>

`GET /v1/tests`

*getAllTests*

<h3 id="getalltestsusingget-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|params|header|string|true|params|

> Example responses

> 200 Response

<h3 id="getalltestsusingget-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|None|

<h3 id="getalltestsusingget-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|*anonymous*|[[Test](#schematest)]|false|none|none|
|» date|string|false|none|none|
|» description|string|false|none|none|
|» id|integer(int64)|false|none|none|
|» img|string|false|none|none|
|» maxScore|integer(int64)|false|none|none|
|» questions|[[Question](#schemaquestion)]|false|none|none|
|»» answers|[[Answer](#schemaanswer)]|false|none|none|
|»»» answer|string|false|none|none|
|»»» answerType|string|false|none|none|
|»»» id|integer(int64)|false|none|none|
|»»» isRight|integer(int32)|false|none|none|
|»» explain|string|false|none|none|
|»» id|integer(int64)|false|none|none|
|»» img|string|false|none|none|
|»» questionText|string|false|none|none|
|»» reward|integer(int32)|false|none|none|
|»» serialNumber|integer(int32)|false|none|none|
|» timeToComplete|string|false|none|none|
|» title|string|false|none|none|

<aside class="success">
This operation does not require authentication
</aside>

## saveTestUsingPOST

<a id="opIdsaveTestUsingPOST"></a>

`POST /v1/tests`

*saveTest*

> Body parameter

```yaml
img: string

```

<h3 id="savetestusingpost-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|params|header|string|true|params|
|title|query|string|true|title|
|date|query|string|true|date|
|description|query|string|false|description|
|timeToComplete|query|string|false|timeToComplete|
|body|body|object|false|none|
|» img|body|string(binary)|false|img|

> Example responses

> 200 Response

<h3 id="savetestusingpost-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Test](#schematest)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|None|

<aside class="success">
This operation does not require authentication
</aside>

## updateTestUsingPATCH

<a id="opIdupdateTestUsingPATCH"></a>

`PATCH /v1/tests`

*updateTest*

> Body parameter

```yaml
img: string

```

<h3 id="updatetestusingpatch-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|params|header|string|true|params|
|id|query|integer(int64)|true|id|
|title|query|string|true|title|
|date|query|string|true|date|
|questions|query|string|false|questions|
|maxScore|query|integer(int32)|false|maxScore|
|description|query|string|false|description|
|timeToComplete|query|string|false|timeToComplete|
|body|body|object|false|none|
|» img|body|string(binary)|false|img|

<h3 id="updatetestusingpatch-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|None|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|No Content|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|

<aside class="success">
This operation does not require authentication
</aside>

## getTestUsingGET

<a id="opIdgetTestUsingGET"></a>

`GET /v1/tests/{id}`

*getTest*

<h3 id="gettestusingget-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer(int64)|true|id|

> Example responses

> 200 Response

<h3 id="gettestusingget-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Test](#schematest)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|None|

<aside class="success">
This operation does not require authentication
</aside>

## deleteTestUsingDELETE

<a id="opIddeleteTestUsingDELETE"></a>

`DELETE /v1/tests/{id}`

*deleteTest*

<h3 id="deletetestusingdelete-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|params|header|string|true|params|
|id|path|integer(int64)|true|id|

> Example responses

> 200 Response

<h3 id="deletetestusingdelete-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|No Content|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|

<h3 id="deletetestusingdelete-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|*anonymous*|[[Test](#schematest)]|false|none|none|
|» date|string|false|none|none|
|» description|string|false|none|none|
|» id|integer(int64)|false|none|none|
|» img|string|false|none|none|
|» maxScore|integer(int64)|false|none|none|
|» questions|[[Question](#schemaquestion)]|false|none|none|
|»» answers|[[Answer](#schemaanswer)]|false|none|none|
|»»» answer|string|false|none|none|
|»»» answerType|string|false|none|none|
|»»» id|integer(int64)|false|none|none|
|»»» isRight|integer(int32)|false|none|none|
|»» explain|string|false|none|none|
|»» id|integer(int64)|false|none|none|
|»» img|string|false|none|none|
|»» questionText|string|false|none|none|
|»» reward|integer(int32)|false|none|none|
|»» serialNumber|integer(int32)|false|none|none|
|» timeToComplete|string|false|none|none|
|» title|string|false|none|none|

<aside class="success">
This operation does not require authentication
</aside>

<h1 id="api-documentation-user-controller">user-controller</h1>

User Controller

## getAllUsersUsingGET

<a id="opIdgetAllUsersUsingGET"></a>

`GET /v1/users`

*getAllUsers*

<h3 id="getallusersusingget-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|params|header|string|true|params|

> Example responses

> 200 Response

<h3 id="getallusersusingget-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|None|

<h3 id="getallusersusingget-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|*anonymous*|[[User](#schemauser)]|false|none|none|
|» activeSticker|[Sticker](#schemasticker)|false|none|none|
|»» cost|integer(int64)|false|none|none|
|»» description|string|false|none|none|
|»» id|integer(int64)|false|none|none|
|»» img|string|false|none|none|
|»» name|string|false|none|none|
|»» quote|string|false|none|none|
|» bdate|string|false|none|none|
|» id|integer(int64)|false|none|none|
|» role|string|false|none|none|
|» school|string|false|none|none|
|» score|integer(int64)|false|none|none|
|» stickers|[[Sticker](#schemasticker)]|false|none|none|

<aside class="success">
This operation does not require authentication
</aside>

## getUserUsingGET

<a id="opIdgetUserUsingGET"></a>

`GET /v1/users/{id}`

*getUser*

<h3 id="getuserusingget-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer(int64)|true|id|
|params|header|string|true|params|

> Example responses

> 200 Response

<h3 id="getuserusingget-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[User](#schemauser)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|None|

<aside class="success">
This operation does not require authentication
</aside>

## saveUserUsingPUT

<a id="opIdsaveUserUsingPUT"></a>

`PUT /v1/users/{id}`

*saveUser*

> Body parameter

```json
{
  "activeSticker": {
    "cost": 0,
    "description": "string",
    "id": 0,
    "img": "string",
    "name": "string",
    "quote": "string"
  },
  "bdate": "string",
  "id": 0,
  "role": "string",
  "school": "string",
  "score": 0,
  "stickers": [
    {
      "cost": 0,
      "description": "string",
      "id": 0,
      "img": "string",
      "name": "string",
      "quote": "string"
    }
  ]
}
```

<h3 id="saveuserusingput-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|params|header|string|true|params|
|id|path|integer(int64)|true|id|
|body|body|[User](#schemauser)|true|user|

> Example responses

> 200 Response

<h3 id="saveuserusingput-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[User](#schemauser)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|None|

<aside class="success">
This operation does not require authentication
</aside>

## buyStickerUsingPATCH

<a id="opIdbuyStickerUsingPATCH"></a>

`PATCH /v1/users/{userId}/buy_sticker/{stickerId}`

*buySticker*

<h3 id="buystickerusingpatch-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|params|header|string|true|params|
|userId|path|integer(int64)|true|userId|
|stickerId|path|integer(int64)|true|stickerId|

> Example responses

> 200 Response

<h3 id="buystickerusingpatch-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[User](#schemauser)|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|No Content|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|

<aside class="success">
This operation does not require authentication
</aside>

## getHistoryUsingGET

<a id="opIdgetHistoryUsingGET"></a>

`GET /v1/users/{userId}/get_history/{testId}`

*getHistory*

<h3 id="gethistoryusingget-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|params|header|string|true|params|
|userId|path|integer(int64)|true|userId|
|testId|path|integer(int64)|true|testId|

> Example responses

> 200 Response

<h3 id="gethistoryusingget-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|None|

<h3 id="gethistoryusingget-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|*anonymous*|[[HistoryEvent](#schemahistoryevent)]|false|none|none|
|» date|string|false|none|none|
|» eventCode|integer(int32)|false|none|none|
|» id|integer(int64)|false|none|none|
|» question|[Question](#schemaquestion)|false|none|none|
|»» answers|[[Answer](#schemaanswer)]|false|none|none|
|»»» answer|string|false|none|none|
|»»» answerType|string|false|none|none|
|»»» id|integer(int64)|false|none|none|
|»»» isRight|integer(int32)|false|none|none|
|»» explain|string|false|none|none|
|»» id|integer(int64)|false|none|none|
|»» img|string|false|none|none|
|»» questionText|string|false|none|none|
|»» reward|integer(int32)|false|none|none|
|»» serialNumber|integer(int32)|false|none|none|
|» score|integer(int64)|false|none|none|

<aside class="success">
This operation does not require authentication
</aside>

## startQuestionUsingPOST

<a id="opIdstartQuestionUsingPOST"></a>

`POST /v1/users/{userId}/send_event/{questionId}`

*startQuestion*

> Body parameter

```json
0
```

<h3 id="startquestionusingpost-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|params|header|string|true|params|
|userId|path|integer(int64)|true|userId|
|questionId|path|integer(int64)|true|questionId|
|body|body|integer(int32)|true|eventCode|

<h3 id="startquestionusingpost-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|None|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|None|

<aside class="success">
This operation does not require authentication
</aside>

## setActiveStickerUsingPATCH

<a id="opIdsetActiveStickerUsingPATCH"></a>

`PATCH /v1/users/{userId}/set_active_sticker/{stickerId}`

*setActiveSticker*

<h3 id="setactivestickerusingpatch-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|params|header|string|true|params|
|userId|path|integer(int64)|true|userId|
|stickerId|path|integer(int64)|true|stickerId|

> Example responses

> 200 Response

<h3 id="setactivestickerusingpatch-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[User](#schemauser)|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|No Content|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|

<aside class="success">
This operation does not require authentication
</aside>

<h1 id="api-documentation-sticker-controller">sticker-controller</h1>

Sticker Controller

## getStickerUsingGET_1

<a id="opIdgetStickerUsingGET_1"></a>

`GET /v1/stickers`

*getSticker*

> Example responses

> 200 Response

<h3 id="getstickerusingget_1-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|None|

<h3 id="getstickerusingget_1-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|*anonymous*|[[Sticker](#schemasticker)]|false|none|none|
|» cost|integer(int64)|false|none|none|
|» description|string|false|none|none|
|» id|integer(int64)|false|none|none|
|» img|string|false|none|none|
|» name|string|false|none|none|
|» quote|string|false|none|none|

<aside class="success">
This operation does not require authentication
</aside>

## addStickerUsingPOST

<a id="opIdaddStickerUsingPOST"></a>

`POST /v1/stickers`

*addSticker*

> Body parameter

```yaml
img: string

```

<h3 id="addstickerusingpost-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|params|header|string|true|params|
|cost|query|integer(int64)|true|cost|
|name|query|string|true|name|
|description|query|string|true|description|
|quote|query|string|true|quote|
|body|body|object|true|none|
|» img|body|string(binary)|true|img|

> Example responses

> 200 Response

<h3 id="addstickerusingpost-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|Inline|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|None|

<h3 id="addstickerusingpost-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|*anonymous*|[[Sticker](#schemasticker)]|false|none|none|
|» cost|integer(int64)|false|none|none|
|» description|string|false|none|none|
|» id|integer(int64)|false|none|none|
|» img|string|false|none|none|
|» name|string|false|none|none|
|» quote|string|false|none|none|

<aside class="success">
This operation does not require authentication
</aside>

## updateStickerUsingPATCH

<a id="opIdupdateStickerUsingPATCH"></a>

`PATCH /v1/stickers`

*updateSticker*

> Body parameter

```json
{
  "cost": 0,
  "description": "string",
  "id": 0,
  "img": "string",
  "name": "string",
  "quote": "string"
}
```

<h3 id="updatestickerusingpatch-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|params|header|string|true|params|
|body|body|[Sticker](#schemasticker)|true|sticker|

<h3 id="updatestickerusingpatch-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|None|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|No Content|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|

<aside class="success">
This operation does not require authentication
</aside>

## getStickerUsingGET

<a id="opIdgetStickerUsingGET"></a>

`GET /v1/stickers/{id}`

*getSticker*

<h3 id="getstickerusingget-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer(int64)|true|id|

> Example responses

> 200 Response

<h3 id="getstickerusingget-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Sticker](#schemasticker)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|None|

<aside class="success">
This operation does not require authentication
</aside>

## deleteStickerUsingDELETE

<a id="opIddeleteStickerUsingDELETE"></a>

`DELETE /v1/stickers/{id}`

*deleteSticker*

<h3 id="deletestickerusingdelete-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|params|header|string|true|params|
|id|path|integer(int64)|true|id|

<h3 id="deletestickerusingdelete-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|None|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|No Content|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|

<aside class="success">
This operation does not require authentication
</aside>

<h1 id="api-documentation-basic-error-controller">basic-error-controller</h1>

Basic Error Controller

## errorHtmlUsingGET

<a id="opIderrorHtmlUsingGET"></a>

`GET /error`

*errorHtml*

> Example responses

> 200 Response

```
{"empty":true,"model":{},"modelMap":{"property1":{},"property2":{}},"reference":true,"status":"100 CONTINUE","view":{"contentType":"string"},"viewName":"string"}
```

<h3 id="errorhtmlusingget-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ModelAndView](#schemamodelandview)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|None|

<aside class="success">
This operation does not require authentication
</aside>

## errorHtmlUsingHEAD

<a id="opIderrorHtmlUsingHEAD"></a>

`HEAD /error`

*errorHtml*

> Example responses

> 200 Response

```
{"empty":true,"model":{},"modelMap":{"property1":{},"property2":{}},"reference":true,"status":"100 CONTINUE","view":{"contentType":"string"},"viewName":"string"}
```

<h3 id="errorhtmlusinghead-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ModelAndView](#schemamodelandview)|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|No Content|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|

<aside class="success">
This operation does not require authentication
</aside>

## errorHtmlUsingPOST

<a id="opIderrorHtmlUsingPOST"></a>

`POST /error`

*errorHtml*

> Example responses

> 200 Response

```
{"empty":true,"model":{},"modelMap":{"property1":{},"property2":{}},"reference":true,"status":"100 CONTINUE","view":{"contentType":"string"},"viewName":"string"}
```

<h3 id="errorhtmlusingpost-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ModelAndView](#schemamodelandview)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|None|

<aside class="success">
This operation does not require authentication
</aside>

## errorHtmlUsingPUT

<a id="opIderrorHtmlUsingPUT"></a>

`PUT /error`

*errorHtml*

> Example responses

> 200 Response

```
{"empty":true,"model":{},"modelMap":{"property1":{},"property2":{}},"reference":true,"status":"100 CONTINUE","view":{"contentType":"string"},"viewName":"string"}
```

<h3 id="errorhtmlusingput-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ModelAndView](#schemamodelandview)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|None|

<aside class="success">
This operation does not require authentication
</aside>

## errorHtmlUsingDELETE

<a id="opIderrorHtmlUsingDELETE"></a>

`DELETE /error`

*errorHtml*

> Example responses

> 200 Response

```
{"empty":true,"model":{},"modelMap":{"property1":{},"property2":{}},"reference":true,"status":"100 CONTINUE","view":{"contentType":"string"},"viewName":"string"}
```

<h3 id="errorhtmlusingdelete-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ModelAndView](#schemamodelandview)|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|No Content|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|

<aside class="success">
This operation does not require authentication
</aside>

## errorHtmlUsingOPTIONS

<a id="opIderrorHtmlUsingOPTIONS"></a>

`OPTIONS /error`

*errorHtml*

> Example responses

> 200 Response

```
{"empty":true,"model":{},"modelMap":{"property1":{},"property2":{}},"reference":true,"status":"100 CONTINUE","view":{"contentType":"string"},"viewName":"string"}
```

<h3 id="errorhtmlusingoptions-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ModelAndView](#schemamodelandview)|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|No Content|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|

<aside class="success">
This operation does not require authentication
</aside>

## errorHtmlUsingPATCH

<a id="opIderrorHtmlUsingPATCH"></a>

`PATCH /error`

*errorHtml*

> Example responses

> 200 Response

```
{"empty":true,"model":{},"modelMap":{"property1":{},"property2":{}},"reference":true,"status":"100 CONTINUE","view":{"contentType":"string"},"viewName":"string"}
```

<h3 id="errorhtmlusingpatch-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[ModelAndView](#schemamodelandview)|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|No Content|None|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|None|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|None|

<aside class="success">
This operation does not require authentication
</aside>

# Schemas

<h2 id="tocS_Answer">Answer</h2>
<!-- backwards compatibility -->
<a id="schemaanswer"></a>
<a id="schema_Answer"></a>
<a id="tocSanswer"></a>
<a id="tocsanswer"></a>

```json
{
  "answer": "string",
  "answerType": "string",
  "id": 0,
  "isRight": 0
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|answer|string|false|none|none|
|answerType|string|false|none|none|
|id|integer(int64)|false|none|none|
|isRight|integer(int32)|false|none|none|

<h2 id="tocS_HistoryEvent">HistoryEvent</h2>
<!-- backwards compatibility -->
<a id="schemahistoryevent"></a>
<a id="schema_HistoryEvent"></a>
<a id="tocShistoryevent"></a>
<a id="tocshistoryevent"></a>

```json
{
  "date": "string",
  "eventCode": 0,
  "id": 0,
  "question": {
    "answers": [
      {
        "answer": "string",
        "answerType": "string",
        "id": 0,
        "isRight": 0
      }
    ],
    "explain": "string",
    "id": 0,
    "img": "string",
    "questionText": "string",
    "reward": 0,
    "serialNumber": 0
  },
  "score": 0
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|date|string|false|none|none|
|eventCode|integer(int32)|false|none|none|
|id|integer(int64)|false|none|none|
|question|[Question](#schemaquestion)|false|none|none|
|score|integer(int64)|false|none|none|

<h2 id="tocS_ModelAndView">ModelAndView</h2>
<!-- backwards compatibility -->
<a id="schemamodelandview"></a>
<a id="schema_ModelAndView"></a>
<a id="tocSmodelandview"></a>
<a id="tocsmodelandview"></a>

```json
{
  "empty": true,
  "model": {},
  "modelMap": {
    "property1": {},
    "property2": {}
  },
  "reference": true,
  "status": "100 CONTINUE",
  "view": {
    "contentType": "string"
  },
  "viewName": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|empty|boolean|false|none|none|
|model|object|false|none|none|
|modelMap|object|false|none|none|
|» **additionalProperties**|object|false|none|none|
|reference|boolean|false|none|none|
|status|string|false|none|none|
|view|[View](#schemaview)|false|none|none|
|viewName|string|false|none|none|

#### Enumerated Values

|Property|Value|
|---|---|
|status|100 CONTINUE|
|status|101 SWITCHING_PROTOCOLS|
|status|102 PROCESSING|
|status|103 CHECKPOINT|
|status|200 OK|
|status|201 CREATED|
|status|202 ACCEPTED|
|status|203 NON_AUTHORITATIVE_INFORMATION|
|status|204 NO_CONTENT|
|status|205 RESET_CONTENT|
|status|206 PARTIAL_CONTENT|
|status|207 MULTI_STATUS|
|status|208 ALREADY_REPORTED|
|status|226 IM_USED|
|status|300 MULTIPLE_CHOICES|
|status|301 MOVED_PERMANENTLY|
|status|302 FOUND|
|status|302 MOVED_TEMPORARILY|
|status|303 SEE_OTHER|
|status|304 NOT_MODIFIED|
|status|305 USE_PROXY|
|status|307 TEMPORARY_REDIRECT|
|status|308 PERMANENT_REDIRECT|
|status|400 BAD_REQUEST|
|status|401 UNAUTHORIZED|
|status|402 PAYMENT_REQUIRED|
|status|403 FORBIDDEN|
|status|404 NOT_FOUND|
|status|405 METHOD_NOT_ALLOWED|
|status|406 NOT_ACCEPTABLE|
|status|407 PROXY_AUTHENTICATION_REQUIRED|
|status|408 REQUEST_TIMEOUT|
|status|409 CONFLICT|
|status|410 GONE|
|status|411 LENGTH_REQUIRED|
|status|412 PRECONDITION_FAILED|
|status|413 PAYLOAD_TOO_LARGE|
|status|413 REQUEST_ENTITY_TOO_LARGE|
|status|414 URI_TOO_LONG|
|status|414 REQUEST_URI_TOO_LONG|
|status|415 UNSUPPORTED_MEDIA_TYPE|
|status|416 REQUESTED_RANGE_NOT_SATISFIABLE|
|status|417 EXPECTATION_FAILED|
|status|418 I_AM_A_TEAPOT|
|status|419 INSUFFICIENT_SPACE_ON_RESOURCE|
|status|420 METHOD_FAILURE|
|status|421 DESTINATION_LOCKED|
|status|422 UNPROCESSABLE_ENTITY|
|status|423 LOCKED|
|status|424 FAILED_DEPENDENCY|
|status|425 TOO_EARLY|
|status|426 UPGRADE_REQUIRED|
|status|428 PRECONDITION_REQUIRED|
|status|429 TOO_MANY_REQUESTS|
|status|431 REQUEST_HEADER_FIELDS_TOO_LARGE|
|status|451 UNAVAILABLE_FOR_LEGAL_REASONS|
|status|500 INTERNAL_SERVER_ERROR|
|status|501 NOT_IMPLEMENTED|
|status|502 BAD_GATEWAY|
|status|503 SERVICE_UNAVAILABLE|
|status|504 GATEWAY_TIMEOUT|
|status|505 HTTP_VERSION_NOT_SUPPORTED|
|status|506 VARIANT_ALSO_NEGOTIATES|
|status|507 INSUFFICIENT_STORAGE|
|status|508 LOOP_DETECTED|
|status|509 BANDWIDTH_LIMIT_EXCEEDED|
|status|510 NOT_EXTENDED|
|status|511 NETWORK_AUTHENTICATION_REQUIRED|

<h2 id="tocS_Question">Question</h2>
<!-- backwards compatibility -->
<a id="schemaquestion"></a>
<a id="schema_Question"></a>
<a id="tocSquestion"></a>
<a id="tocsquestion"></a>

```json
{
  "answers": [
    {
      "answer": "string",
      "answerType": "string",
      "id": 0,
      "isRight": 0
    }
  ],
  "explain": "string",
  "id": 0,
  "img": "string",
  "questionText": "string",
  "reward": 0,
  "serialNumber": 0
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|answers|[[Answer](#schemaanswer)]|false|none|none|
|explain|string|false|none|none|
|id|integer(int64)|false|none|none|
|img|string|false|none|none|
|questionText|string|false|none|none|
|reward|integer(int32)|false|none|none|
|serialNumber|integer(int32)|false|none|none|

<h2 id="tocS_Sticker">Sticker</h2>
<!-- backwards compatibility -->
<a id="schemasticker"></a>
<a id="schema_Sticker"></a>
<a id="tocSsticker"></a>
<a id="tocssticker"></a>

```json
{
  "cost": 0,
  "description": "string",
  "id": 0,
  "img": "string",
  "name": "string",
  "quote": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|cost|integer(int64)|false|none|none|
|description|string|false|none|none|
|id|integer(int64)|false|none|none|
|img|string|false|none|none|
|name|string|false|none|none|
|quote|string|false|none|none|

<h2 id="tocS_Test">Test</h2>
<!-- backwards compatibility -->
<a id="schematest"></a>
<a id="schema_Test"></a>
<a id="tocStest"></a>
<a id="tocstest"></a>

```json
{
  "date": "string",
  "description": "string",
  "id": 0,
  "img": "string",
  "maxScore": 0,
  "questions": [
    {
      "answers": [
        {
          "answer": "string",
          "answerType": "string",
          "id": 0,
          "isRight": 0
        }
      ],
      "explain": "string",
      "id": 0,
      "img": "string",
      "questionText": "string",
      "reward": 0,
      "serialNumber": 0
    }
  ],
  "timeToComplete": "string",
  "title": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|date|string|false|none|none|
|description|string|false|none|none|
|id|integer(int64)|false|none|none|
|img|string|false|none|none|
|maxScore|integer(int64)|false|none|none|
|questions|[[Question](#schemaquestion)]|false|none|none|
|timeToComplete|string|false|none|none|
|title|string|false|none|none|

<h2 id="tocS_User">User</h2>
<!-- backwards compatibility -->
<a id="schemauser"></a>
<a id="schema_User"></a>
<a id="tocSuser"></a>
<a id="tocsuser"></a>

```json
{
  "activeSticker": {
    "cost": 0,
    "description": "string",
    "id": 0,
    "img": "string",
    "name": "string",
    "quote": "string"
  },
  "bdate": "string",
  "id": 0,
  "role": "string",
  "school": "string",
  "score": 0,
  "stickers": [
    {
      "cost": 0,
      "description": "string",
      "id": 0,
      "img": "string",
      "name": "string",
      "quote": "string"
    }
  ]
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|activeSticker|[Sticker](#schemasticker)|false|none|none|
|bdate|string|false|none|none|
|id|integer(int64)|false|none|none|
|role|string|false|none|none|
|school|string|false|none|none|
|score|integer(int64)|false|none|none|
|stickers|[[Sticker](#schemasticker)]|false|none|none|

<h2 id="tocS_View">View</h2>
<!-- backwards compatibility -->
<a id="schemaview"></a>
<a id="schema_View"></a>
<a id="tocSview"></a>
<a id="tocsview"></a>

```json
{
  "contentType": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|contentType|string|false|none|none|

