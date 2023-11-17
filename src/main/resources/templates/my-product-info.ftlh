<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <#list images as image>
                <li data-target="#myCarousel" data-slide-to="0" class=""></li>
            </#list>
        </ol>
        <div class="carousel-inner">
            <#list images as image>
                <div class="carousel-item <#if image.previewImage>active</#if>">
                    <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg"
                         role="img" aria-label=" :  " preserveAspectRatio="xMidYMid slice" focusable="false">
                        <title></title>
                        <rect width="100%" height="100%" fill="#777"></rect>
                        <text x="50%" y="50%" fill="#777" dy=".3em"></text>
                    </svg>

                    <div class="container">
                        <div class="carousel-caption">
                            <a href="/images/${image.id}">
                                <img class="product" style="height: 100px" src="/images/${image.id}">
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

    <#if product.isNotModerating()>
        <h style="text-align: center">Объявление находится на этапе модерации, подождите пока администратор проверит ваше объявление</h><hr>
    <#else>
        <#if product.isNotActive()>
            <h style="text-align: center">Объявление заблокированно администратором по причине: ${product.productAdminInfo.deactivateReason}</h><hr>
            <h style="text-align: center">Вы можете отредатировать объявление и отправить на повторную модерацию:</h><hr>
        <#else>
            <h style="text-align: center">Объявление активно</h><hr>
        </#if>
    </#if>
    <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="true" aria-controls="collapseExample">
       Редактировать
    </a>
    <div class="collapse mb-3" id="collapseExample" style="">
        <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
           data-toggle="collapse" href="#collapseExample2" role="button"
           aria-expanded="true" aria-controls="collapseExample">
           Редактировать название товара
        </a>
        <div class="collapse mb-3" id="collapseExample2" style="">
            <form action="/product/change-name/${product.id}" method="post">
                <div class="form-group">
                    <label for="productName">Название товара</label>
                    <input type="text" required class="form-control"
                           id="newName" name="newName">
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <button type="submit" style="width: 100%" class="btn btn-dark">Изменить</button>
            </form>
        </div>

        <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
           data-toggle="collapse" href="#collapseExample3" role="button"
           aria-expanded="true" aria-controls="collapseExample">
           Редактировать цену товара
        </a>

        <div class="collapse mb-3" id="collapseExample3" style="">
            <form action="/product/change-price/${product.id}" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="price">Цена товара</label>
                    <div class="input-group mb-3">
                        <input type="number" required class="form-control" id="newPrice" name="newPrice">
                        <div class="input-group-append">
                            <span class="input-group-text">₽</span>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <button type="submit" style="width: 100%" class="btn btn-dark">Изменить</button>
            </form>
        </div>

        <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
           data-toggle="collapse" href="#collapseExample4" role="button"
           aria-expanded="true" aria-controls="collapseExample">
           Редактировать город товара
        </a>

        <div class="collapse mb-3" id="collapseExample4" style="">
            <form action="/product/change-city/${product.id}" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="searchCity">Город</label>
                    <select id="newCity" required name="newCity" class="form-control">
                        <option value="">Город не выбран</option>

                        <option value="Москва">Москва</option>
                        <option value="Красноярск">Красноярск</option>
                        <option value="Казань">Казань</option>

                    </select>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <button type="submit" style="width: 100%" class="btn btn-dark">Изменить</button>
            </form>
        </div>

        <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
           data-toggle="collapse" href="#collapseExample5" role="button"
           aria-expanded="true" aria-controls="collapseExample">
           Редактировать описание товара
        </a>

        <div class="collapse mb-3" id="collapseExample5" style="">
            <form action="/product/change-description/${product.id}" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="description">Описание товара</label>
                    <textarea required class="form-control" id="newDescription" name="newDescription"></textarea>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <button type="submit" style="width: 100%" class="btn btn-dark">Изменить</button>
            </form>
        </div>

        <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
           data-toggle="collapse" href="#collapseExample6" role="button"
           aria-expanded="true" aria-controls="collapseExample">
           Редактировать фотографии товара
        </a>

        <div class="collapse mb-3" id="collapseExample6" style="">
            <form action="/product/change-photos/${product.id}" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <div class="custom-file">
                        <input required type="file" name="file1" id="file1">
                        <label class="custom-file-label" for="file1">Первая фотография к товару</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file2" id="file2">
                        <label class="custom-file-label" for="file2">Вторая фотография к товару</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file3" id="file3">
                        <label class="custom-file-label" for="file3">Третья фотография к товару</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <button type="submit" style="width: 100%" class="btn btn-dark">Изменить</button>
            </form>
        </div>
    </div>
</@t.template>