package com.example.Ejemplo.Service;

import com.example.Ejemplo.Entity.Productos;
import com.example.Ejemplo.Repository.ProductosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosServiceImplements implements ProductosService {

    private final ProductosRepository productosRepository;

    public ProductosServiceImplements(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    @Override
    public List<Productos> getAllProductos() {
        return productosRepository.findAll();
    }

    @Override
    public Productos getProductosById(Integer id) {
        Productos producto = productosRepository.findById(id).orElse(null);
        if (producto == null) {
            throw new IllegalArgumentException("Producto no encontrado");
        }
        return productosRepository.findById(id).orElse(null);
    }

    @Override
    public Productos saveProductos(Productos productos) throws RuntimeException {
        return productosRepository.save(productos);
    }

    @Override
    public Productos updateProductos(Integer id, Productos productos) {
        Productos producto = productosRepository.findById(id).orElse(null);
        if (producto != null) {
            producto.setNombre_producto(productos.getNombre_producto());
            producto.setPrecio(productos.getPrecio());
            producto.setStock(productos.getStock());
            producto.setEstado(productos.getEstado());
        } else {
            throw new IllegalArgumentException("Producto no encontrado");
        }
        return productosRepository.save(producto);
    }

    @Override
    public void deleteProductos(Integer id) {
        Productos producto = productosRepository.findById(id).orElse(null);
        if (producto == null) {
            throw new IllegalArgumentException("Producto no encontrado");
        }
        productosRepository.deleteById(id);
    }
}