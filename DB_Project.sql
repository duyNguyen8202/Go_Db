Create Database FigureShop;

use FigureShop;

create table address
( id int auto_increment,
    name       varchar(200),
    constraint pk_address primary key (id)
);
create table user
(
    id int      auto_increment,
    firstname       varchar(32) not null,
    lastname        varchar(32) not null,
    id_card         varchar(9) unique,
    email           varchar(50) unique,
    phone           varchar(12) unique,
    isEmailActive   boolean   default false,
    isPhoneActive   boolean   default false,
    password 		varchar(50) not null,
    role            varchar(50) not null default 'user',
    avatar          varchar(100),
    eWallet        double  default 0,
    createdAt       timestamp default now(),
    updatedAt        timestamp default now() on update now(),
    constraint pk_user primary key (id)
);
create table user_address
(
    addressId int,
    userId    int,
    constraint pk_user_address primary key (addressId, userId),
    constraint fk_user foreign key (userId) references user (id),
    constraint fk_address foreign key (addressId) references address (id)
);
create table store
(
    id 			int   auto_increment,
    name         varchar(100) not null unique,
    bio          varchar(255) not null,
    ownerId      int not null,
    isOpen       boolean   default true,
    avatar       varchar(100),
    rating       int       default 3,
    eWallet     double   default 0,
    createdAt    timestamp default now(),
    updatedAt     timestamp default now() on update now(),
    constraint pk_store primary key (id),
    constraint fk_owner foreign key (ownerId) references user (id),
    constraint check_store_rating check (0 <= rating <= 5)
);

create table image_store
(
	id int auto_increment,
    name varchar(255),
    storeId int,
    constraint pk_image_store primary key(id),
    constraint fk_image_store foreign key(storeId) references store(id)
);

create table category
(
    id int auto_increment,
    name       varchar(32) not null unique,
    isDeleted  boolean   default false,
    createdAt  timestamp default now(),
    updatedAt   timestamp default now() on update now(),
    constraint pk_category primary key (id)
);

create table product
(
    id int       auto_increment,
    name             varchar(100) not null unique,
    description      varchar(1000) not null,
    price            double not null,
    promotionalPrice double not null,
    quantity         int     not null,
    sold             int     not null,
    isActive         boolean default true,
    categoryId       int  not null,
    storeId          int  not null,
    rating           int     default 3,
    createdAt        timestamp default now(),
    updatedAt        timestamp default now() on update now(),

    constraint pk_product primary key (id),
    constraint fk_product_store foreign key (storeId) references store (id),
    constraint fk_product_category foreign key (categoryId) references category (id),
    constraint check_product_rating check (0 <= rating <= 5),
    constraint check_product_sold check (sold > 0),
    constraint check_product_quantity check (quantity > 0),
    constraint check_product_promotionalPrice check (promotionalPrice > 0),
    constraint check_product_price check (price > 0),
    constraint check_product_name check (length(name) <= 100)
);

create table image_product
(
	id int auto_increment,
    name varchar(255),
    productId int,
    constraint pk_image_product primary key(id),
    constraint fk_image_product foreign key(productId) references product(id)
);

create table delivery
(
    id int  auto_increment,
    name        varchar(100) not null unique,
    description varchar(1000) not null,
    price       double not null,
    isDeleted   boolean default false,
    createdAt   timestamp default now(),
    updatedAt   timestamp default now() on update now(),

    constraint pk_delivery primary key (id),
    constraint check_delivery_price check (price > 0),
    constraint check_delivery_name check (length(name) <= 100)
);


create table userfollowstore
(
    id int auto_increment,
    userId     int not null,
    storeId    int not null,
    createdAt  timestamp null,
    updatedAt  timestamp null,

    constraint pk_userfollowstore primary key (id),
    constraint fk_userfollowstore_store foreign key (storeId) references store (id),
    constraint fk_userfollowstore_user foreign key (userId) references user (id)
);

create table userfollowproduct
(
    id int auto_increment,
    userId     int not null,
    productId  int not null,
    createdAt  timestamp null,
    updatedAt  timestamp null,

    constraint pk_userfollowproduct primary key (id),
    constraint fk_userfollowproduct_product foreign key (productId) references product (id),
    constraint fk_userfollowproduct_user foreign key (userId) references user (id)
);

create table orders
(
    id int      auto_increment,
    userId          int  not null,
    storeId         int  not null,
    deliveryId      int  not null,
    address         varchar(255) not null,
    phone           varchar(10) not null,
    status          varchar(50) default 'not processed',
    amountFromUser  double not null,
    amountToStore   double not null,
    amountToGD      double not null,
    createdAt       timestamp default now(),
    updatedAt       timestamp default now() on update now(),

    constraint pk_orders primary key (id),
    constraint fk_orders_user foreign key (userId) references user (id),
    constraint fk_orders_store foreign key (storeId) references store (id),
    constraint fk_oder_delivery foreign key (deliveryId) references delivery (id),
    constraint check_orders_amountFromUser check (amountFromUser >= 0),
    constraint check_orders_amountToStore check (amountToStore >= 0),
    constraint check_orders_amountToGD check (amountToGD >= 0)
);

create table review
(
    id int        not null auto_increment,
    userId    int  not null,
    productId int  not null,
    storeId   int  not null,
    ordersId  int  not null,
    content   varchar(1001) not null,
    stars     int not null,
    createdAt timestamp default now(),
    updatedAt timestamp default now() on update now(),

    constraint pk_review primary key (id),
    constraint fk_review_user foreign key (userId) references user (id),
    constraint fk_review_product foreign key (productId) references product (id),
    constraint fk_review_store foreign key (storeId) references store (id),
    constraint fk_review_orders foreign key (ordersId) references orders (id),
    constraint check_review_content check (length(content) <= 1000),
    constraint check_review_stars check (0 <= stars <= 5)
);

create table ordersItem
(
    id int        not null auto_increment,
    ordersId  int  not null,
    productId int  not null,
    count     int not null,
    createdAt timestamp default now(),
    updatedAt timestamp default now() on update now(),

    constraint pk_ordersItem primary key (id),
    constraint fk_ordersItem_orders foreign key (ordersId) references orders (id),
    constraint fk_ordersItem_product foreign key (productId) references product (id),
    constraint check_ordersItem_count check (count >= 1)
);

create table cart
(
    id int       not null auto_increment,
    userId    int not null,
    storeId   int not null,
    createdAt timestamp default now(),
    updatedAt timestamp default now() on update now(),

    constraint pk_cart primary key (id),
    constraint fk_cart_user foreign key (userId) references user (id),
    constraint fk_cart_store foreign key (storeId) references store (id)
);

create table cartItem
(
    id int       not null auto_increment,
    cartId    int not null,
    productId int not null,
    count     int not null,
    createdAt timestamp default now(),
    updatedAt timestamp default now() on update now(),

    constraint pk_cartItem primary key (id),
    constraint fk_cartItem_card foreign key (cartId) references cart (id),
    constraint fk_cartItem_product foreign key (productId) references product (id),
    constraint check_cartItem_count check (count >= 1)
);

create table transaction
(
    id int        not null auto_increment,
    userId    int  not null,
    storeId   int  not null,
    isUp      boolean not null,
    amount    double not null,
    createdAt timestamp default now(),
    updatedAt timestamp default now() on update now(),

    constraint pk_transaction primary key (id),
    constraint fk_transaction_user foreign key (userId) references user (id),
    constraint fk_transaction_store foreign key (storeId) references store (id)
);

insert into address (name) values (N'Quận 1, TP. Hồ Chí Minh');
insert into address (name) values (N'Quận 2, TP. Hồ Chí Minh');
insert into address (name) values (N'Quận 3, TP. Hồ Chí Minh');
insert into address (name) values (N'Quận 4, TP. Hồ Chí Minh');
insert into address (name) values (N'Quận 5, TP. Hồ Chí Minh');
insert into address (name) values (N'Quận 6, TP. Hồ Chí Minh');
insert into address (name) values (N'Quận 7, TP. Hồ Chí Minh');
insert into address (name) values (N'Quận 8, TP. Hồ Chí Minh');
insert into address (name) values (N'Quận 9, TP. Hồ Chí Minh');
insert into address (name) values (N'Quận 10, TP. Hồ Chí Minh');
insert into address (name) values (N'Quận 11, TP. Hồ Chí Minh');
insert into address (name) values (N'Quận 12, TP. Hồ Chí Minh');
insert into address (name) values (N'Quận Bình Thạnh, TP. Hồ Chí Minh');
insert into address (name) values (N'Thành phố Thủ Đức, TP. Hồ Chí Minh');

insert into user (firstname,lastname,id_card,email,phone,password,avatar) 
values (N'Tiến',N'Trần Lê Minh','0001','Tientlm@gmail.com',0949614209,'123456',N'av0001');
insert into user (firstname,lastname,id_card,email,phone,password,avatar) 
values (N'Tuyến',N'Nguyễn Thiện','0002','Tuyennt@gmail.com',0123456789,'123456',N'av0002');
insert into user (firstname,lastname,id_card,email,phone,password,avatar) 
values (N'Lập',N'nguyễn Hữu','0003','Lapnh@gmail.com',0987654321,'123456',N'av0003');
insert into user (firstname,lastname,id_card,email,phone,password,avatar) 
values (N'Thọ',N'TNịnh Đức','0004','Thond@gmail.com',0234567891,'123456',N'av0004');
insert into user (firstname,lastname,id_card,email,phone,password,avatar) 
values (N'Quý',N'Võ Ngọc','0005','Quyvn@gmail.com',0147852369,'123456',N'av0005');

insert into user_address (addressId,userId) values (10,1);
insert into user_address (addressId,userId) values (8,2);
insert into user_address (addressId,userId) values (6,3);
insert into user_address (addressId,userId) values (4,4);
insert into user_address (addressId,userId) values (2,5);

insert into store (name,bio,ownerId,avatar) values (N'st1',N'Bio1',1,N'avt0001');
insert into store (name,bio,ownerId,avatar) values (N'st2',N'Bio2',2,N'avt0002');
insert into store (name,bio,ownerId,avatar) values (N'st3',N'Bio3',3,N'avt0003');
insert into store (name,bio,ownerId,avatar) values (N'st4',N'Bio4',4,N'avt0004');
insert into store (name,bio,ownerId,avatar) values (N'st5',N'Bio4',5,N'avt0005');

insert into image_store (name,storeId) values (N'Store 1',1);
insert into image_store (name,storeId) values (N'Store 2',2);
insert into image_store (name,storeId) values (N'Store 3',3);
insert into image_store (name,storeId) values (N'Store 4',4);
insert into image_store (name,storeId) values (N'Store 5',5);

insert into category (name) value (N'Danh mục số 1');
insert into category (name) value (N'Danh mục số 2');
insert into category (name) value (N'Danh mục số 3');
insert into category (name) value (N'Danh mục số 4');
insert into category (name) value (N'Danh mục số 5');
insert into category (name) value (N'Danh mục số 6');
insert into category (name) value (N'Danh mục số 7');
insert into category (name) value (N'Danh mục số 8');
insert into category (name) value (N'Danh mục số 9');
insert into category (name) value (N'Danh mục số 10');

insert into product (name,description,price,promotionalPrice,quantity,sold,categoryId,storeId)
values (N'Mô hình 1',N'Diễn giải mô hình số 1',100000,95000,20,5,1,1);
insert into product (name,description,price,promotionalPrice,quantity,sold,categoryId,storeId)
values (N'Mô hình 2',N'Diễn giải mô hình số 2',150000,145000,25,15,2,1);
insert into product (name,description,price,promotionalPrice,quantity,sold,categoryId,storeId)
values (N'Mô hình 3',N'Diễn giải mô hình số 3',160000,150000,40,5,1,2);
insert into product (name,description,price,promotionalPrice,quantity,sold,categoryId,storeId)
values (N'Mô hình 4',N'Diễn giải mô hình số 4',90000,85000,60,50,2,2);
insert into product (name,description,price,promotionalPrice,quantity,sold,categoryId,storeId)
values (N'Mô hình 5',N'Diễn giải mô hình số 5',50000,45000,65,15,3,2);
insert into product (name,description,price,promotionalPrice,quantity,sold,categoryId,storeId)
values (N'Mô hình 6',N'Diễn giải mô hình số 6',40000,35000,50,45,4,3);
insert into product (name,description,price,promotionalPrice,quantity,sold,categoryId,storeId)
values (N'Mô hình 7',N'Diễn giải mô hình số 7',100000,90000,20,5,4,3);
insert into product (name,description,price,promotionalPrice,quantity,sold,categoryId,storeId)
values (N'Mô hình 8',N'Diễn giải mô hình số 8',110000,105000,35,15,5,3);
insert into product (name,description,price,promotionalPrice,quantity,sold,categoryId,storeId)
values (N'Mô hình 9',N'Diễn giải mô hình số 9',80000,75000,60,35,5,3);

insert into image_product (name,productId) values (N'Hình số 1',1);
insert into image_product (name,productId) values (N'Hình số 2',2);
insert into image_product (name,productId) values (N'Hình số 3',3);
insert into image_product (name,productId) values (N'Hình số 4',4);
insert into image_product (name,productId) values (N'Hình số 5',5);
insert into image_product (name,productId) values (N'Hình số 6',6);
insert into image_product (name,productId) values (N'Hình số 7',7);
insert into image_product (name,productId) values (N'Hình số 8',8);
insert into image_product (name,productId) values (N'Hình số 9',9);

insert into delivery (name,description,price) values (N'Chuyển phát nhanh',N'Giao hàng trong vòng 7 ngày',11000);
insert into delivery (name,description,price) values (N'Ninja Van',N'Giao hàng ít nhất 5 ngày',17000);
insert into delivery (name,description,price) values (N'Hàng không',N'Giao hàng trong vòng 4 ngày',22000);
insert into delivery (name,description,price) values (N'Viettel Post',N'Giao hàng trong vòng 5 ngày',30000);

insert into userfollowstore (userId,storeId) values (1,1);
insert into userfollowstore (userId,storeId) values (1,2);
insert into userfollowstore (userId,storeId) values (1,5);
insert into userfollowstore (userId,storeId) values (2,1);
insert into userfollowstore (userId,storeId) values (2,3);
insert into userfollowstore (userId,storeId) values (2,5);
insert into userfollowstore (userId,storeId) values (3,1);
insert into userfollowstore (userId,storeId) values (3,4);
insert into userfollowstore (userId,storeId) values (4,2);
insert into userfollowstore (userId,storeId) values (4,5);
insert into userfollowstore (userId,storeId) values (5,5);

insert into userfollowproduct (userId,productId) values (1,1);
insert into userfollowproduct (userId,productId) values (1,3);
insert into userfollowproduct (userId,productId) values (1,6);
insert into userfollowproduct (userId,productId) values (1,9);
insert into userfollowproduct (userId,productId) values (2,2);
insert into userfollowproduct (userId,productId) values (2,6);
insert into userfollowproduct (userId,productId) values (2,8);
insert into userfollowproduct (userId,productId) values (3,4);
insert into userfollowproduct (userId,productId) values (3,6);
insert into userfollowproduct (userId,productId) values (4,1);
insert into userfollowproduct (userId,productId) values (4,2);
insert into userfollowproduct (userId,productId) values (4,5);
insert into userfollowproduct (userId,productId) values (4,7);
insert into userfollowproduct (userId,productId) values (5,2);
insert into userfollowproduct (userId,productId) values (5,9);

insert into orders (userId,storeId,deliveryId,address,phone,amountFromUser,amountToStore,amountToGD)
values (1,1,1,N'Bình Tân - Hồ Chí Minh',0123456789,5,5,4);
insert into orders (userId,storeId,deliveryId,address,phone,amountFromUser,amountToStore,amountToGD)
values (1,3,2,N'Bình Tân - Hồ Chí Minh',0123456789,6,7,4);
insert into orders (userId,storeId,deliveryId,address,phone,amountFromUser,amountToStore,amountToGD)
values (2,2,2,N'Thủ Đức - Hồ Chí Minh',0987654321,10,11,5);
insert into orders (userId,storeId,deliveryId,address,phone,amountFromUser,amountToStore,amountToGD)
values (3,4,4,N'Quận 1 - Hồ Chí Minh',0231564897,5,6,5);
insert into orders (userId,storeId,deliveryId,address,phone,amountFromUser,amountToStore,amountToGD)
values (4,5,2,N'Quận 2 - Hồ Chí Minh',0321456987,2,2,2);
insert into orders (userId,storeId,deliveryId,address,phone,amountFromUser,amountToStore,amountToGD)
values (5,1,3,N'Bình Thạnh - Hồ Chí Minh',0369741258,6,10,5);

insert into review (userId,productId,storeId,ordersId,content,stars) values(1,5,1,1,N'Sản phẩm khá tốt',5);
insert into review (userId,productId,storeId,ordersId,content,stars) values(1,3,2,2,N'Sản phẩm còn hạn chế, giao hàng trễ hạn',4);
insert into review (userId,productId,storeId,ordersId,content,stars) values(2,6,3,3,N'Sản phẩm có khuyết điểm',4);
insert into review (userId,productId,storeId,ordersId,content,stars) values(3,7,4,4,N'Sản phẩm khá tốt',5);
insert into review (userId,productId,storeId,ordersId,content,stars) values(4,8,1,5,N'Sản phẩm khá ổn',4);
insert into review (userId,productId,storeId,ordersId,content,stars) values(5,5,5,5,N'Sản phẩm khá tốt',5);

insert into ordersitem (ordersId,productId,count) values(1,1,12);
insert into ordersitem (ordersId,productId,count) values(2,2,2);
insert into ordersitem (ordersId,productId,count) values(3,3,4);
insert into ordersitem (ordersId,productId,count) values(4,5,6);
insert into ordersitem (ordersId,productId,count) values(5,6,7);
insert into ordersitem (ordersId,productId,count) values(6,7,8);

insert into cart(userId,storeId) values (1,1);
insert into cart(userId,storeId) values (1,2);
insert into cart(userId,storeId) values (1,3);
insert into cart(userId,storeId) values (1,4);
insert into cart(userId,storeId) values (1,5);
insert into cart(userId,storeId) values (2,1);
insert into cart(userId,storeId) values (2,2);
insert into cart(userId,storeId) values (2,3);
insert into cart(userId,storeId) values (2,4);
insert into cart(userId,storeId) values (2,5);
insert into cart(userId,storeId) values (3,1);
insert into cart(userId,storeId) values (3,2);
insert into cart(userId,storeId) values (3,3);
insert into cart(userId,storeId) values (3,4);
insert into cart(userId,storeId) values (3,5);
insert into cart(userId,storeId) values (4,1);
insert into cart(userId,storeId) values (4,2);
insert into cart(userId,storeId) values (4,3);
insert into cart(userId,storeId) values (4,4);
insert into cart(userId,storeId) values (4,5);
insert into cart(userId,storeId) values (5,1);
insert into cart(userId,storeId) values (5,2);
insert into cart(userId,storeId) values (5,3);
insert into cart(userId,storeId) values (5,4);
insert into cart(userId,storeId) values (5,5);

insert into cartitem(cartId,productId,count) values(1,1,2);
insert into cartitem(cartId,productId,count) values(1,4,5);
insert into cartitem(cartId,productId,count) values(1,4,2);
insert into cartitem(cartId,productId,count) values(1,9,5);
insert into cartitem(cartId,productId,count) values(2,3,3);
insert into cartitem(cartId,productId,count) values(2,6,1);
insert into cartitem(cartId,productId,count) values(2,6,2);
insert into cartitem(cartId,productId,count) values(2,7,2);
insert into cartitem(cartId,productId,count) values(2,8,2);
insert into cartitem(cartId,productId,count) values(3,1,5);
insert into cartitem(cartId,productId,count) values(3,6,4);
insert into cartitem(cartId,productId,count) values(3,9,5);
insert into cartitem(cartId,productId,count) values(4,1,2);
insert into cartitem(cartId,productId,count) values(4,4,5);
insert into cartitem(cartId,productId,count) values(5,3,2);
insert into cartitem(cartId,productId,count) values(5,4,3);
insert into cartitem(cartId,productId,count) values(5,5,2);
insert into cartitem(cartId,productId,count) values(5,4,5);

insert into transaction (userId,storeId,isUp,amount) values(1,1,0,2);
insert into transaction (userId,storeId,isUp,amount) values(1,2,0,1);
insert into transaction (userId,storeId,isUp,amount) values(2,3,0,2);
insert into transaction (userId,storeId,isUp,amount) values(3,4,0,3);
insert into transaction (userId,storeId,isUp,amount) values(4,5,1,2);
insert into transaction (userId,storeId,isUp,amount) values(5,3,1,2);


use FigureShop;


select E.storeName, E.userId, E.id, cartitem.productId, cartitem.count from
(select D.storeName, D.userId, D.storeId, D.id from (select store.name as storeName, A.userId, A.id, A.storeId from( SELECT cart.storeId, cart.id, cart.userId from cart) As A inner join store on store.id=A.storeId) As D where D.userId=1 ) E
inner join cartitem on E.id=cartitem.cartId;



select C.count, C.name, C.promotionalprice, A.storeName, A.userId from
(select * from( SELECT store.name as storeName, cart.id, cart.userId from cart inner join store on store.id=cart.storeId) D where D.userId=1 )As A
inner join
(select B.id, B.count, product.name, product.promotionalprice from
(select cart.id, cartitem.productId, cartitem.count from cartitem inner join cart on cart.id=cartitem.cartId) B inner join product on product.id= B.productId) C on A.id=C.id;
