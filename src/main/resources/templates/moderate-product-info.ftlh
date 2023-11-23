<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div id="myCarousel" class="carousel slide my-custom-carousel" data-ride="carousel">
        <ol class="carousel-indicators">
            <#list images as image>
                <li data-target="#myCarousel" data-slide-to="0" class=""></li>
            </#list>
        </ol>
        <div class="carousel-inner">
            <#list images as image>
                <div class="carousel-item <#if image.previewImage>active</#if>">
                    <svg class="bd-placeholder-img" width="100%" height="500" xmlns="http://www.w3.org/2000/svg"
                         role="img" aria-label=" :  " preserveAspectRatio="xMidYMid slice" focusable="false">
                        <title></title>
                        <rect width="100%" height="100%" fill="#777"></rect>
                        <text x="50%" y="50%" fill="#777" dy=".3em"></text>
                    </svg>

                    <div class="container">
                        <div class="carousel-caption">
                            <a href="/images/${image.id}">
                                <img class="product-image-carousel" src="/images/${image.id}">
                            </a>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
        <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <hr>
    <p><h1 style="text-align: center">${product.title} | ${product.price} ₽</h1></p>
    <p style="text-align: center">${product.description}</p>
    <p style="text-align: center">Номер телефона продавца: <b>${product.user.sellerData.phone}</b></p>
    <p style="text-align: center">Дата создания объявления: ${product.dateOfCreated}</b></p>
    <p style="text-align: center">Дата последнего обновления объявления: ${product.dateOfLastChanging}</b></p>


    <hr>
    <p style="text-align: center">
        <#if product.user.isNonAvatar()>
            <a href="/user/${product.user.id}">
                <img src="/static/images/avatar.png" alt="mdo" width="32" height="32" class="rounded-circle"> ${product.user.name}
            </a>
        <#else>
            <a href="/user/${product.user.id}">
                <img src="/images/${product.user.avatar.id}" alt="mdo" width="32" height="32" class="rounded-circle"> ${product.user.name}
            </a>
        </#if>
    </p>

    <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
           data-toggle="collapse" href="#collapseExample" role="button"
           aria-expanded="true" aria-controls="collapseExample">
            Блокировать объявление
    </a>
    <div class="collapse mb-3" id="collapseExample" style="">
        <form action="/admin/moderation/product/${product.id}?moderateResult=false" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="productName">Причина блокировки</label>
                <input type="text" required class="form-control"
                       id="productName" name="deactivateReason">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <button type="submit" style="width: 100%" class="btn btn-dark">Заблокировать</button>
        </form>
    </div>

    <form action="/admin/moderation/product/${product.id}?moderateResult=true" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" style="width: 100%" class="btn btn-dark">Пропустить</button>
    </form>
</@t.template>