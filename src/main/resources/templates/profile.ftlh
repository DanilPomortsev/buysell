<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Личный кабинет</h1>
    <hr>
    <br>
    <div style="text-align: center">
        <#if user.isNonAvatar()>
            <img src="/static/images/avatar.png" style="border:2px solid black; border-radius: 20px" height="120">
        <#else>
            <img src="/images/${user.avatar.id}" style="border:2px solid black; border-radius: 20px" height="120">
        </#if>
        <br>
        <br>
        <h2>${user.name}</h2>
        <br>
        Электоронная почта: <b>${user.email}</b>
        <br>
        <#if user.isSeller()>
            Номер телефона: <b>${user.sellerData.phone}</b><br>
            Адресс: <b>${user.sellerData.address}</b><br>
            Контактная ссылка: <b>${user.sellerData.contact}</b><br>
        </#if>
        <br>
        <#if user.isAdmin()>
            <a class="btn btn-dark my-3" style="text-align: center; width: 100%" href="/admin">
                Панель администратора
            </a><br>
        </#if>

        <form action="/avatar" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="file">
                    <label class="custom-file-label" for="file">Изменить фотографию профиля</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <button type="submit" style="width: 100%" class="btn btn-dark">Изменить</button>
        </form>

        <a class="btn btn-dark my-3" style="text-align: center; width: 100%" href="/like">
            Избранные товары
        </a>

        <#if user.isNotSeller()>
            <a class="btn btn-dark my-3" style="text-align: center; width: 100%" href="/seller-data">
                Стать продавцом
            </a>
        </#if>

        <#if user.isSeller()>
            <a class="btn btn-dark my-3" style="text-align: center; width: 100%" href="/seller-data">
                Данные продавца
            </a>
            <a class="btn btn-dark my-3" style="text-align: center; width: 100%" href="/my/products">
                Мои товары
            </a>
        </#if>

        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="text-align: center; width: 100%" class="btn btn-danger mb-2">
                Выйти из аккаунта
            </button>
        </form>
    </div>
</@t.template>