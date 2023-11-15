## Описание

Ссылка на проект (https://github.com/DanilPomortsev/buysell)

## Наименование

Название проекта: "BuySellMarket". Веб-приложение, предназначенное для покупки, продажи и поиска бу товаров. Оно упрощает процесс поиска нужного товара для покупателя, ускоряет процесс коммуникации между продавцом и покупателем, предоставляет продавцу удобную платформу для размещения и продажи своих товаров.

## Предметная область

Предметная область проекта охватывает большой спектр людей, проживающих в разных городах, и предоставляет функционал, необходимый для поиска и продажи бу товаров. Проект помогает пользователям наладить коммуникацию друг с другом и осуществить желаемую покупку или продажу. Особое внимание уделяется приятному и интуитивно понятному визуальному интерфейсу и комфорту использования для каждого пользователя.

## Данные

### Необходимые пользовательские данные

- Email: используется как основной и единственный логин для авторизации на сайте, должен быть уникальным.
- Пароль: используется для авторизации.
- Имя пользователя.

### Данные для внутреннего использования

- Дата создания аккаунта.
- Статус пользователя: активен ли аккаунт. Администратор по той или иной причине может заблокировать пользователя, и в таком случае он не сможет иметь доступ к сервису.

### Опциональные данные пользователя

- Ваш аватар: фото, которое будут видеть другие пользователи при просмотре ваших объявлений.

### Данные, необходимые продавцу

Пользователи имеют разные роли в нашем сервисе, и лишь те пользователи, которые оставили следующие данные о себе, могут не только просматривать объявления других пользователей, но и добавлять свои. За счёт того что продавец оставляет свой номер телефона и другие удобные для него способы связаться с ним (ссылки в мессенджеры), покупатель может с лёгкостью связаться с продавцом.

- Номер телефона: необходим чтобы пользователь мог с вами связаться, если ваше объявление заинтересовало его.
- Адрес: необходим чтобы пользователь, заинтересованный вашим объявлением, мог понимать ваше географическое положение.
- Ссылки на выши контакты: необходим чтобы пользователь мог с вами связаться, если ваше объявление заинтересовало его. Например ссылка на мессенджер, где вам будет удобно обсудить с покупателем детали сделки.

### Товары

Необходимо, чтобы продавец мог выложить достаточно информации о товаре, чтобы пользователи могли полностью понять, какой товар предлагает к продаже продавец, а также без проблем могли связаться с продавцом в случае, если товар их заинтересовал. После добавления объявления продавец может деактивировать его если объявление уже не актуально по причине: продажт или какой либо иной причине. Также объявление может деактивировать администратор в случае если оно содержит не желательный контент или было добавлено давно. В случае деактивации объявление будет полностью удалено и соответственно не будет отображаться в ленте объявлений и не будет участвовать в глобальном поиске.

- Название объявления: по нему осуществляется глобальный поиск объявлений.
- Подробное описание товара: позволяет продавцу полностью описать товар.
- Стоимость товара.
- Город: город, в котором располагается товар, является одним из параметров в поиске объявлений.
- Список фотографий товара: фотографии, которые продавец загружает для более наглядного описания товара.
- Дата создания объявления: позволяет администратору удалять старые неактуальные объявления и позволяет пользователю видеть, как давно объявление было добавлено.

### Избранное

У пользователей есть возможность добавлять все объявления к себе в избранное, чтобы позже просмотреть их. Это позволяет пользователям удобнее взаимодействовать с объявлениями и не терять понравившиеся товары, а также экономить время на их поиск.

- Избранное: нам необходимо хранить список избранных товаров для пользователей и список пользователей, которым понравился товар.

### Изображения

- Фотографии: необходимо хранить фотографии товаров, фотографии профилей пользователей и дополнительную информацию о этих файлах.inger
- Export documents as Markdown, HTML and PDF

# Ограничения целостности

## Данные

- Пользователь: Email должен быть уникальным в рамках приложения и соответствовать email паттерну. Пароль должен обладать достаточным уровнем сложности. Пароль и имя пользователя не могут превышать соответствующей длины. 

- Продавец: Номер телефона продавца должен соответствовать паттерну номера телефона. Адрес не может превышать соответствующей длины. 

- Товар: Город товара выбирается среди доступных вариантов. Описание и название товара не могут превышать соответствующую длину. Цена не может быть отрицательной и не может превышать соответствующей величины. 

- Фотографии: Размер файла фотографий не может превышать 100 мегабайт. 

## Общие ограничения целостности

Общие ограничения целостности достигаются за счёт валидаций разного уровня: валидация значений данных, валидация доступа к действию, валидация корректности связи между сущностями. 

# Пользовательские роли

- Пользователь: USERROLE. Обычный пользователь, которому позволено просматривать объявления и добавлять их себе в избранное. Количество таких пользователей произвольное. 

- **Продавец**: SELLERROLE. Продавец-пользователь, которому позволено просматривать объявления и добавлять их себе в избранное. А также размещать собственные объявления. Количество таких пользователей произвольное. 

- Администратор: ADMINROLE. Пользователь, которому позволено просматривать объявления и добавлять их себе в избранное. А также деактивировать не желательные объявления и банить пользователей с ролями SELLERROLE. Количество таких пользователей определяется главным администратором. 

- Главный администратор: MAINADMINROLE. Пользователь, которому позволено просматривать объявления и добавлять их себе в избранное. А также деактивировать не желательные объявления и банить пользователей с ролями SELLERROLE. Забирать статус ADMINROLE. Такой пользователь единственен.

# UI

Проект реализован в виде Web-сайта 

### Панель регистрации пользователя 
![Логотип](https://i.imgur.com/2Yzq3s3.jpg)

### Панель авторизации пользователя 
![Логотип](https://i.imgur.com/DpzelCS.jpeg)

### Профиль пользователя 
![Логотип](https://i.imgur.com/Q7JadT3.jpg)

### Панель добавления товара 
![Логотип](https://i.imgur.com/7vR0THF.jpg)

### Страница товара
![Логотип](https://i.imgur.com/DX2WMJR.jpg)

### Список собственных товаров 
![Логотип](https://i.imgur.com/Qy0r27f.jpg)

### Список избранных товаров 
![Логотип](https://i.imgur.com/zysrDc2.jpg)

### Общий список товаров / поиск товаров 
![Логотип](https://i.imgur.com/ymxoLzv.jpg)

# Язык программирования 

 - Java 

# Технологии разработки  

- Java Spring Fraimwork: серверная часть приложения 
- Spring Data JPA: библиотека для ORM работы с базой данных 
- FTLH: средство отображения динамических данных на страницах 
- CSS: общее оформление стилей Web-приложения 
- Docker: средство контейнеризации и развёртывания приложения

# СУБД 

- PostgreSQL


## Схема базы данных
![Логотип](DataBaseScheme.png)


# Тестирование: 

1. Unit Тестирование: 

   - Создание Unit тестов, покрывающих функционал приложения   
   - Проверка соответствия данных ожидаемым значениям 

2. Тестирование пользовательского интерфейса (UI):   

   - Тестирование корректности работы пользовательского интерфейса 
   - Тестирование корректности работы функционала соответствующего пользовательского интерфейса 

3. Тестирование безопасности: 

   - Тестирование на отсутствие ошибок в праве доступа к функционалу, соответствующему разным пользовательским ролям
