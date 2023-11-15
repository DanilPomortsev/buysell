<#import "blocks/template.ftlh" as t>
<@t.template user>
    <#if user.isSeller()>
        <h1 style="text-align: center">Ваши данные продавца</h1><hr>
        Номер телефона: <b>${user.sellerData.phone}</b><br>
        Адресс: <b>${user.sellerData.address}</b><br>
        Контактная ссылка: <b>${user.sellerData.contact}</b><br>
    </#if>
    <#if user.isNotSeller()>
        <h1 style="text-align: center">Вы еще не стали продавцом, введите данные чтобы стать</h1><hr>
    </#if>
    <form action="/seller-data" method="post">
            <div class="form-group">
                <label for="phone">Номер телефона</label>
                <input type="text" class="form-control" required id="phone" name="phone">
            </div>
            <div class="form-group">
                <label for="email">Адресс</label>
                <input type="text" class="form-control" id="address" required name="address">
            </div>
            <div class="form-group">
                <label for="password">Сылка на ваш контакт</label>
                <input type="text" class="form-control" id="contact" required name="contact">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <#if user.isNotSeller()>
                <button type="submit" style="width: 100%" class="btn btn-dark">Отправить</button>
            </#if>

            <#if user.isSeller()>
                <button type="submit" style="width: 100%" class="btn btn-dark">Изменить</button>
            </#if>
        </form>
        <#if errorMessage??>
            <h2 style="color: red">${errorMessage}</h2>
        </#if>
</@t.template>