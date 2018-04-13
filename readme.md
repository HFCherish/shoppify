是一个微服务项目，是一个在线购物平台。使用 docker、spring cloud 部署微服务。创建micro-server, micro-front，构建 saas 应用，最大化微前端、微服务的复用、灵活组配。

# model
![model](./model.jpg)

# Tasks(28.5h)

## product service: sor - 60'
* POST /stores/{storeName}/products - 30'
	* 201 and save
* GET /products - 20'
	* 200 get
	* get by store
* GET /products/{id} - 10'
	* 200 get

## product pricing service: sor - 130'
* POST /products/{id}/pricings - 30'
	* 201 & save
	* check product exists
* GET /products/{id}/pricings - 20'
	* 200 & get
	* check product exists
* GET /pricings/current
	* 200 & get - 30'
	* get by product id - 20'
	* can return the product info at the same time - 30'

## product inventory service: sor - 80'
* POST /products/{id}/inventories - 30'
	* 201 & save
	* check product exists
* POST /outbound-orders - 30'
* GET /products/{id}/inventories/current - 20'
	* 200 & get
	* can return the product info at the same time

## dockerize - 260'
* product service
	* create image - 20'
	* configure spring-cloud register server - 20'
	* service discovery - 20'
* product pricing service
	* create image - 20'
	* service discovery - 20'
	* connect product service using service discovery - 30'
* product inventory service
	* create image - 20'
	* service discovery - 20'
	* connect product service using service discovery - 30
* nginx
	* config reverse-proxy using nginx to access the three service - 60'

## product detail view - 510'
* project env preparation - 240'
* can show product detail: static - 120'
* can show product price: static - 30'
* can show product from product price service - 120'

## product list view - 160'
* can show product list: static - 60'
* can show product list from product price service - 40'
* can show product detail - 60'

## product management view - 510'
* can show add product: static - 60'
* can show add product using product service - 60'
* show product detail after adding - 120'
* dockerize
	* create image - 60'
	* config reverse-proxy using nginx with a custom domain name to access the product management service - 60'
* can show menu with add product - 120'
* can show menu with product list - 30'
