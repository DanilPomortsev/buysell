<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Избранные товары</h1><hr>
    <div class="card-columns">
            <#list products as product>
                <a href="/product/${product.id}" class="product-link">
                    <div class="card text-white bg-dark my-3 product">

                        <img src="/images/${product.previewImageId}"
                             class="card-img-top">
                        <div class="card-body">
                            <h5 class="card-title">${product.title}</h5>
                            <p class="card-text">${product.price} ₽</p>
                        </div>
                        <div class="card-footer">
                            ${product.user.name}
                        </div>
                    </div>
                </a>
            <#else>
                <h1 style="text-align: center">Не найдено</h1>
            </#list>
        </div>
</@t.template>