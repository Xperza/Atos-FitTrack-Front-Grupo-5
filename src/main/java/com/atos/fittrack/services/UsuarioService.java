package com.atos.fittrack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.atos.fittrack.entities.Usuario;
import com.atos.fittrack.repository.UsuarioRepository;

@Service
public class UsuarioService implements ICRUD<Usuario,Integer>{

	@Autowired
	private UsuarioRepository usuarioDao;
	
	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	public Optional<Usuario> findById(Integer id) {
		return usuarioDao.findById(id);
	}

	@Override
	public void delete(Integer id) {
		usuarioDao.deleteById(id);
	}
	
	public Optional <Usuario> findOneByEmail(String email) {
		return usuarioDao.findOneByEmail(email);
		
	}

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioDao.findOneByEmail(email);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return new User(usuario.getEmail(), usuario.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado con email: " + email);
        }
    }

}

