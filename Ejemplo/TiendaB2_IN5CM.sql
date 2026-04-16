drop database if exists TiendaB2_IN5CM;
create database TiendaB2_IN5CM;
use TiendaB2_IN5CM;

Create table Clientes (
    dpi_cliente int not null auto_increment,
    nombre_cliente varchar(50),
    apellido_cliente varchar(50),
    direccion varchar(100),
    estado int,
    primary key PK_dpi_cliente(dpi_cliente)
);

Create table Usuarios (
    codigo_usuario int not null auto_increment,
    username varchar(45),
    password varchar(45),
    email varchar(60),
    rol varchar(45),
    estado int,
    primary key PK_codigo_usuario(codigo_usuario)
);


Create table Productos (
    codigo_producto int not null auto_increment,
    nombre_producto varchar(60),
    precio decimal(10,2),
    stock int,
    estado int,
    primary key PK_codigo_producto(codigo_producto)
);

Create table Ventas (
	codigo_venta int not null auto_increment,
    fecha_venta date,
    total decimal (10,2),
    estado int,
    Clientes_dpi_cliente int,
    Usuarios_codigo_usuario int,
	primary key PK_codigo_venta(codigo_venta),
    constraint Fk_Clientes_dpi_cliente foreign key(Clientes_dpi_cliente)
    references Clientes(dpi_cliente) on delete cascade,
    constraint Fk_Usuarios_codigo_usuario foreign key(Usuarios_codigo_usuario)
    references Usuarios(codigo_usuario) on delete cascade
);

Create table DetalleVenta (
	codigo_detalle_venta int not null,
    cantidad int,
    precio_unitario decimal(10,2),
    subtotal decimal(10,2),
    Productos_codigo_producto int,
    Ventas_codigo_venta int,
    primary key PK_codigo_detalle_venta(codigo_detalle_venta),
    constraint Fk_Productos_codigo_producto foreign key(Productos_codigo_producto)
    references Productos(codigo_producto) on delete cascade,
    constraint Fk_Ventas_codigo_venta foreign key(Ventas_codigo_venta)
    references Ventas(codigo_venta) on delete cascade
);	

-- Procedimientos almacenados
delimiter //
	create procedure sp_MostrarUsuarios()
		Begin 
        select codigo_usuario, username, password, rol, estado from Usuarios;
	End //
delimiter ;

delimiter //
	create procedure sp_ingresarUsuarios(in username VARCHAR(45),
											in password varchar(45),
                                            in email varchar(60),
                                            in rol varchar(45),   
                                            in estado int)
		Begin
		insert into Usuarios(username, password, email,
                                rol, estado)
				values (username, password, email, rol, estado);
End //
delimiter ;

call sp_ingresarUsuarios('admin','123','admin@mail.com','ADMIN',1);
call sp_ingresarUsuarios('user1','123','user1@mail.com','USER',1);
call sp_ingresarUsuarios('user2','123','user2@mail.com','USER',1);
call sp_ingresarUsuarios('user3','123','user3@mail.com','USER',1);
select * from Usuarios;

delimiter //
	create procedure sp_updateUsuarios(in usname VARCHAR(45),
										in passwordNuevo varchar(45),
										in emailNuevo varchar(60),
										in rolNuevo varchar(45),   
										in estadoNuevo int)
		begin
		update Usuarios
		set username = usname,
				password = passwordNuevo,
                email = emailNuevo,
                rol = rolNuevo,
                estado = estadoNuevo
		Where id_proveedor = Id;
End //

delimiter ;

-- Mostrar Clientes
delimiter //
create procedure sp_MostrarClientes()
begin 
    select dpi_cliente, nombre_cliente, apellido_cliente, direccion, estado from Clientes;
end //
delimiter ;


-- Ingresar Clientes
delimiter //
create procedure sp_IngresarClientes(in nombre varchar(50),
									in apellido varchar(50),
									in direccion_cliente varchar(100),
									in estado_cliente int
)
begin
    insert into Clientes(nombre_cliente, apellido_cliente, direccion, estado)
    values (nombre, apellido, direccion_cliente, estado_cliente);
end //
delimiter ;

call sp_IngresarClientes('Carlos', 'García', '7ma Avenida, Zona 1, Guatemala', 1);
call sp_IngresarClientes('Ana', 'Martínez', 'Calle Real, Antigua Guatemala', 1);
call sp_IngresarClientes('Luis', 'Rodríguez', 'Barrio El Centro, Quetzaltenango', 0);
call sp_IngresarClientes('María', 'López', 'Residenciales Los Olivos, Zona 18', 1);
call sp_IngresarClientes('José', 'Pérez', 'Km. 15 Carretera a El Salvador', 1);
call sp_IngresarClientes('Elena', 'Cano', 'Vía 4, Zona 4, Ciudad de Guatemala', 0);
call sp_IngresarClientes('Fernando', 'Morales', 'Avenida Las Américas, Zona 13', 1);
call sp_IngresarClientes('Lucía', 'Herrera', 'Condominio El Frutal, Villa Nueva', 1);
call sp_IngresarClientes('Ricardo', 'Méndez', 'Sector 3, San Cristóbal, Mixco', 1);
call sp_IngresarClientes('Sofía', 'Vásquez', 'Colonia El Maestro, Zona 15', 0);
select * from Clientes;

-- Actualizar Clientes
delimiter //
create procedure sp_ActualizarCliente(
    in dpi int, 
    in direccionNueva varchar(100),
    in estadoNuevo int
)
begin
    update Clientes
    set direccion = direccionNueva,
        estado = estadoNuevo
    where dpi_cliente = dpi;
end //
delimiter ;


-- Eliminar Clientes
delimiter //
create procedure sp_EliminarCliente(in dpi int)
begin
    delete from Clientes
    where dpi_cliente = dpi;
end //
delimiter ;

-- Mostrar Productos
delimiter //
create procedure sp_MostrarProductos()
begin 
    select codigo_producto, nombre_producto, precio, stock, estado from Productos;
end //
delimiter ;


-- Ingresar Productos
delimiter //
create procedure sp_IngresarProductos(in nombre varchar(60),
										in precio_producto decimal(10,2),
										in stock_producto int,
										in estado_producto int)
begin
    insert into Productos(nombre_producto, precio, stock, estado)
    values (nombre, precio_producto, stock_producto, estado_producto);
end //
delimiter ;

call sp_IngresarProductos('Leche Deslactosada 1L', 14.50, 50, 1);
call sp_IngresarProductos('Arroz Blanco 2lb', 12.00, 100, 1);
call sp_IngresarProductos('Frijoles Negros Volteo', 8.75, 80, 1);
call sp_IngresarProductos('Aceite Vegetal 800ml', 22.30, 40, 1);
call sp_IngresarProductos('Azúcar Caña Real 1kg', 7.50, 150, 1);
call sp_IngresarProductos('Pasta de Dientes 100ml', 18.00, 60, 1);
call sp_IngresarProductos('Jabón de Tocador', 5.25, 200, 1);
call sp_IngresarProductos('Detergente en Polvo 1kg', 15.00, 45, 1);
call sp_IngresarProductos('Café Molido 400g', 35.00, 30, 1);
call sp_IngresarProductos('Cereal de Maíz', 28.90, 25, 1);

-- Actualizar Productos
delimiter //
create procedure sp_ActualizarProducto(
    in codigo int,
    in precioNuevo decimal(10,2),
    in stockNuevo int,
    in estadoNuevo int
)
begin
    update Productos
    set precio = precioNuevo,
        stock = stockNuevo,
        estado = estadoNuevo
    where codigo_producto = codigo;
end //
delimiter ;


-- Eliminar Productos
delimiter //
create procedure sp_EliminarProducto(in codigo int)
begin
    delete from Productos
    where codigo_producto = codigo;
end //
delimiter ;