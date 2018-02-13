package com.dsg.desguambios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dsg.desguambios.entidades.Desguace;
import com.dsg.desguambios.entidades.Producto;
import com.dsg.desguambios.repositorios.DesguaceRepository;
import com.dsg.desguambios.repositorios.ProductoRepository;



@Controller
public class DesguambiosController {
		
		//public ArrayList<Desguace> lista = new ArrayList<>();
	
		@Autowired
		private Desguace instanciaDesguace = null;
		@Autowired
		private DesguaceRepository desguaceRepository;
		@Autowired
		private ProductoRepository productoRepository;
		//@Autowired
		public List<Producto> listaProductos = new ArrayList<>();
		
		
		@PostConstruct
		public void init() {
			Desguace desguacePepe = new Desguace("Pepe","pepe@gmail.com","Calle epep 123","pepe","pepe");
			Producto Producto_A= new Producto("Retrovisor","Calle epep 123","Pepe",1);
			Producto Producto_B= new Producto("Luna","Calle epep 123","Pepe",2);
			Producto Producto_C= new Producto("Puerta","Calle epep 123","Pepe",3);
			
			Desguace desguaceLuis = new Desguace("Luis","pepe@gmail.com","Calle siul 123","luis","luis");
			Producto Producto_D= new Producto("Retrovisor","Calle siul 123","Luis",1);
			Producto Producto_E= new Producto("Luna","Calle siul 123","Luis",2);
			Producto Producto_F= new Producto("Puerta","Calle siul 123","Luis",3);
			
			desguaceRepository.save(desguacePepe);
			productoRepository.save(Producto_A);
			productoRepository.save(Producto_B);
			productoRepository.save(Producto_C);
			
			desguaceRepository.save(desguaceLuis);
			productoRepository.save(Producto_D);
			productoRepository.save(Producto_E);
			productoRepository.save(Producto_F);
			
			//listaProductos.add(Producto_A);
			//listaProductos.add(Producto_B);
			//lista.add(desguacePepe);
		}
		
		@RequestMapping("/")
		public String webIndex(Model model) {
			return "index";
		}
		
		/*
		public Desguace buscarDes(String usuario) {
			Desguace d = new Desguace();
			
			for(Desguace ds : lista) {
				if(ds.getUsuario().equals(usuario)) {
					d = ds;
				}
			}		
			
			return d;
		}
		}*/
		
		@RequestMapping("/datosAlHacerLogin")
		public String datosAlHacerLogin(Model model,@RequestParam String usuario,@RequestParam String password){
			instanciaDesguace =desguaceRepository.findByUsuario(usuario);
			if(instanciaDesguace.getUsuario().equals(usuario)&& instanciaDesguace.getPassword().equals(password)) {
				return "subirEliminarEditar";
			}else {
				return "index";
			}
			
		}
		
	
		@RequestMapping("/login")
		public String weblogin(Model model) {
			
			return "login";
		}
		
		@RequestMapping(value = "/nuevoRegistro")
		public String nuevoRegistro(Model model) {
			
			return "registro";
		}
		@RequestMapping(value = "/subirEliminarEditar")
		public String subirEliminarEditar(Model model) {
			
			return "subirEliminarEditar";
		}
		
		
		@RequestMapping(value = "/subirProducto")
		public String subirProducto1(Model model) {
			
			model.addAttribute("nombredesguacepropietario",instanciaDesguace.getUsuario());
			model.addAttribute("direccionpropietario",instanciaDesguace.getDireccion());
			return "subirProducto";
		}
		
		@RequestMapping(value = "/subirNuevoProducto")
		public String subirNuevoProducto(Model model,@RequestParam String litProducto,@RequestParam String direccionpropietario,@RequestParam String nombredesguacepropietario,@RequestParam String id_marca) {
			int nM = Integer.parseInt(id_marca);
			Producto producto = new Producto ( litProducto,  direccionpropietario,  nombredesguacepropietario,nM);
			productoRepository.save(producto);
			//listaProductos.add(producto);
			//listaProductos.add(Producto_A);
			String usuario=instanciaDesguace.getUsuario();
			List<Producto> listaProductos=(List<Producto>) productoRepository.findByUsuario(usuario);
			model.addAttribute("listaProductos",listaProductos);
			return "verMisProductos";
		}
		
		@RequestMapping(value = "/verMisProductos")
		public String verMisProducto(Model model) {
			String usuario=instanciaDesguace.getUsuario();
			List<Producto> listaProductos=(List<Producto>) productoRepository.findByUsuario(usuario);
			model.addAttribute("listaProductos",listaProductos);
			return "verMisProductos";
		}
		
		
		@RequestMapping(value = "/editarProducto")
		public String vistaEditarProducto(Model model) {
			
			return "buscadorEditarProducto";
		}
		
		//Falta por hacer con base de datos
		@RequestMapping(value = "/subirProductoEditado")
		public String subirProductoEditado(Model model,@RequestParam String litProducto,@RequestParam String direccionpropietario,@RequestParam String nombredesguacepropietario,@RequestParam String id_marca) {
			int nM = Integer.parseInt(id_marca);
			Producto producto = new Producto ( litProducto,  direccionpropietario,  nombredesguacepropietario,nM);
			productoRepository.save(producto);
			//listaProductos.add(producto);
			//listaProductos.add(Producto_A);
			String usuario=instanciaDesguace.getUsuario();
			List<Producto> listaProductos=(List<Producto>) productoRepository.findByUsuario(usuario);
			model.addAttribute("listaProductos",listaProductos);
			return "verMisProductos";
		}
		
		//Falta por hacer con base de datos
		public Producto buscarP(String idProducto) {
			Producto p= new Producto();
			for(Producto producto : listaProductos) {
				if(producto.getLitProducto().equals(idProducto)) {
				   p=producto;
					
				}
			}
			return p;
		}
		//Hecho con base de datos, falta por informar al usuario el identificador de la pieza que esta subiendo
		@RequestMapping(value = "/datosEditarProducto")
		public String datosEditarProducto(Model model,@RequestParam Long idProducto) {
			
			//Producto producto;
			//producto = buscarP(idProducto);
			Producto producto=productoRepository.findByIdProducto(idProducto);
			model.addAttribute("litProducto",producto.getLitProducto());
			model.addAttribute("id_marca",producto.getIdMarca());
			model.addAttribute("nombredesguacepropietario",producto.getUsuario());
			model.addAttribute("direccionpropietario",producto.getDirEmpresa());
			//listaProductos.remove(producto);
			productoRepository.delete(producto);
			return "editarProducto";
		}
		
		
		
		 @RequestMapping(value = "/registro")
		 public String registro(Model model,@RequestParam String alta, String usuario,String email,
				  String direccion,  String password,  String valPassword) {
			
			if(Integer.parseInt(alta)==1) {
				Desguace instanciaDesguace = new Desguace(usuario,email,direccion,password,valPassword);
				desguaceRepository.save(instanciaDesguace);
				//lista.add(desguace);
				//lista.add(desguace1);
				model.addAttribute("mensaje", true);
				
			} else {
				model.addAttribute("mensaje", false);
			}
			 
			return "registro";
				 
			 
		 } 
		 
		 @RequestMapping(value = "/eliminarProducto")
			public String vistaEliminarProducto(Model model) {
				
				return "buscadorEliminarProducto";
			}
		 
		 
		 
			@RequestMapping(value = "/datosEliminarProducto")
			public String datosEliminarProducto(Model model,@RequestParam Long idProducto) {
				
				//Producto producto;
				//producto = buscarP(idProducto);
				Producto producto=productoRepository.findByIdProducto(idProducto);
				//listaProductos.remove(producto);
				productoRepository.delete(producto);
				return "verMisProductos";
			}
		 /*
		 @RequestMapping(value = "/prueba")
		 public String registro(Model model) {
			
			 model.addAttribute("usuario", lista.get(0).getUsuario());
			 model.addAttribute("direccion", lista.get(0).getDireccion());
			 model.addAttribute("password", lista.get(0).getPassword());
			 model.addAttribute("email", lista.get(0).getEmail());
			 model.addAttribute("valPassword", lista.get(0).getValPassword());
			 
			 model.addAttribute("usuario1", lista.get(1).getUsuario());
			 model.addAttribute("direccion1", lista.get(1).getDireccion());
			 model.addAttribute("password1", lista.get(1).getPassword());
			 model.addAttribute("email1", lista.get(1).getEmail());
			 model.addAttribute("valPassword1", lista.get(1).getValPassword());

			 
			 
			return "prueba";
			 
			 
			 
		 }		*/ 
		 
		 
		 
	}