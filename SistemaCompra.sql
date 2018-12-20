create database SistemaCompra;

create table Producto (
	idProducto int NOT NULL AUTO_INCREMENT,
	descripcion varchar(50),
	tamaño int,
	precio float,
	tiempo_entrega int,
	primary key(idProducto));
alter table Producto add column existencia int;

update Producto
set existencia=100;

create table Usuario(
	idUsuario int not null auto_increment,
	nombre varchar(50),
	apellido varchar(50),
	primary key(idUsuario)
);

create table Carrito (
	idCarrito int NOT NULL AUTO_INCREMENT,
	idUsuario int,
	primary key(idCarrito),
	foreign key (idUsuario) references Usuario(idUsuario)
);
alter table Carrito drop foreign key Carrito_ibfk_1;
alter table Carrito add foreign key (idUsuario) references Usuario(idUsuario);

create table ColorProducto(
	idProducto int,
	color varchar(50),
	foreign key(idProducto) references Producto(idProducto)
);
alter table ColorProducto drop foreign key ColorProducto_ibfk_1;
alter table ColorProducto add foreign key(idProducto) references Producto(idProducto) ON DELETE CASCADE ON UPDATE CASCADE;


create table ImagenProducto(
	idProducto int,
	url varchar(100),
	foreign key(idProducto) references Producto(idProducto)
);
alter table ImagenProducto drop foreign key ImagenProducto_ibfk_1;
alter table ImagenProducto add foreign key(idProducto) references Producto(idProducto) ON DELETE CASCADE ON UPDATE CASCADE; 
alter table ImagenProducto add column imagen BLOB;
alter table ImagenProducto drop column url;
alter table ImagenProducto add column idImagen int PRIMARY KEY AUTO_INCREMENT;
alter table ImagenProducto add primary key (idImagen); 
create table CarritoProductos(
	idCarrito int not null,
	idProducto int,
	Cantidad int,
	foreign key(idCarrito) references Carrito(idCarrito),
	foreign key(idProducto) references Producto(idProducto)
);
alter table CarritoProductos drop foreign key CarritoProductos_ibfk_1;
alter table CarritoProductos add foreign key (idCarrito) references Carrito(idCarrito) ON DELETE CASCADE ON UPDATE CASCADE;

alter table CarritoProductos drop foreign key CarritoProductos_ibfk_2;
alter table CarritoProductos add foreign key (idProducto) references Producto(idProducto) ON DELETE CASCADE ON UPDATE CASCADE;

insert into Producto(descripcion,tamaño,precio,tiempo_entrega) values
('Detergente liquido',12,78.50,4),
('Escoba',5,12.50,2),
('Papas fritas',2,2.76,1),
('Refresco',5,45.23,2),
('Maruchan',7,99.99,6),
('Bisteck',55,67.33,2),
('Papas',59,34.81,6),
('Silla',2,89.12,3),
('Lapiz',1,675.2,21),
('Libro',2,8001,23);

insert into ColorProducto(idProducto,color) values
(1,'Azul'),
(1,'Verde'),
(1,'Negro'),
(1,'Amazul'),
(2,'Azul'),
(2,'Caoba'),
(2,'Amarillo'),
(3,'Verde'),
(3,'Cielo'),
(3,'Guinda');

insert into Usuario(nombre, apellido) values
('Jose','Jimenez'),
('Manuel','Camargo'),
('Alma','Marcela');

insert into Carrito(idUsuario) values
(2),
(3);

insert into CarritoProductos(idCarrito,idProducto,cantidad) values
(1,2,6),
(1,4,8),
(1,8,1),
(2,3,90),
(2,10,1);

INSERT INTO ImagenProducto(idProducto, url) values
(1,'1.jpeg'),
(1,'2.jpeg'),
(1,'3.jpeg'),
(2,'4.jpeg'),
(2,'5.jpeg'),
(2,'6.jpeg');
(3,'7.jpeg'),
(3,'8.jpg'),
(4,'9.jpg'),
(4,'10.jpg'),
(4,'11.jpg');



CREATE PROCEDURE ObtenerCarritoUsuario (in idUsuarioParam int)
Select Producto.idProducto,Producto.descripcion,Producto.tamaño,Producto.precio,Producto.tiempo_entrega,Producto.existencia 
from Carrito 
INNER JOIN CarritoProductos on (Carrito.idCarrito=CarritoProductos.idCarrito AND Carrito.idUsuario=idUsuarioParam) 
INNER JOIN Producto ON Producto.idProducto=CarritoProductos.idProducto; 

CREATE PROCEDURE ObtenerColoresProducto(in idProductoParam int)
SELECT ColorProducto.color from Producto INNER JOIN ColorProducto on (Producto.idProducto=ColorProducto.idProducto) 
where Producto.idProducto=idProductoParam;

CREATE PROCEDURE InsertarProducto(in descripcionParam varchar(50), in tamañoParam int, in precioParam float, in tiempo_entregaParam int, in existenciaParam int)
INSERT INTO Producto(descripcion, tamaño, precio, tiempo_entrega, existencia) values
(descripcionParam, tamañoParam, precioParam, tiempo_entregaParam, existenciaParam);

CREATE PROCEDURE EliminarProducto(in idProductoParam int)
DELETE FROM Producto where idProducto=idProductoParam;

CREATE PROCEDURE ActualizarProducto(in idProductoParam int, in descripcionParam varchar(50), in tamañoParam int, in precioParam int, in tiempo_entregaParam int, in existenciaParam int)
UPDATE Producto
SET descripcion=descripcionParam, tamaño=tamañoParam, precio=precioParam, tiempo_entrega=tiempo_entregaParam, existencia=existenciaParam
WHERE idProducto=idProductoParam;

CREATE PROCEDURE ObtenerImagenes(in idProductoParam int)
SELECT imagen FROM ImagenProducto where idProducto=idProductoParam 

CREATE PROCEDURE AgregarAlCarrito(in idCarritoParam int ,in IdProductoParam int ,in	CantidadParam int, out disponible INT)
@ExistenciaP :=select existencia from Producto where idProducto=idProductoParam
if CantidadParam<@existenciaP then
set disponible=1
else then
set fisponible=0;
end if;
