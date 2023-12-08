<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Регистрация</h1>
    <hr>
    <form action="/registration" method="post">
        <div class="form-group">
            <label for="name">Имя пользователя</label>
            <input type="text" class="form-control" required id="name" name="name">
            <#if errors??>
                <#if errors.hasFieldErrors("name")>
                    <#list errors.getFieldErrors("name") as error>
                        <p style="color: red">${error.defaultMessage}</p>
                    </#list>
                </#if>
            </#if>
        </div>
        <div class="form-group">
            <label for="email">Электронная почта</label>
            <input type="email" class="form-control" id="email" required name="email">
            <#if errors??>
                <#if errors.hasFieldErrors("email")>
                    <#list errors.getFieldErrors("email") as error>
                        <p style="color: red">${error.defaultMessage}</p>
                    </#list>
                </#if>
            </#if>
        </div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <input type="password" class="form-control" id="password" required name="password">
            <#if errors??>
                <#if errors.hasFieldErrors("password")>
                    <#list errors.getFieldErrors("password") as error>
                        <p style="color: red">${error.defaultMessage}</p>
                    </#list>
                </#if>
            </#if>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-dark">Зарегистрироваться</button>
    </form>
    <#if errorMessage??>
        <h2 style="color: red">${errorMessage}</h2>
    </#if>
</@t.template>