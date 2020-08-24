package com.example.demo.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.plaf.multi.MultiOptionPaneUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.IUsuarioDAO;
import com.example.demo.entity.Usuario;

@Controller
public class UsuarioController {
	
	@Autowired
	private IUsuarioDAO usuarioDao;
	
	@GetMapping("/")
	public String usersList(Model model) {
		model.addAttribute("usuarios",usuarioDao.findAll());
		return "usersList";
	}
	
	@GetMapping("/form")
	public String userForm(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "userForm";
	}
	
	@PostMapping("/save")
	public String save(Usuario usuario, @RequestParam(name = "file", required = false)MultipartFile foto) {
		
		if(!foto.isEmpty()) {
			String ruta = "C://Temp//uploads";
			
			try {
				byte[]bytes = foto.getBytes();
				Path rutaAbsoulta = Paths.get(ruta + "//" + foto.getOriginalFilename());
				Files.write(rutaAbsoulta, bytes);
				usuario.setFoto(foto.getOriginalFilename());
				
				
				
			}catch (Exception e) {
				
			}
			
			usuarioDao.save(usuario);
			
			
			
			
		}
		
		
		
		
		return "redirect:/";
	}
	
	
	
}
