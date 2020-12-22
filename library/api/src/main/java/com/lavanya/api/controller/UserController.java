package com.lavanya.api.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.lavanya.api.model.User;
import com.lavanya.api.service.UserService;


/**
 * Rest Controller used in MVC architecture to control all the requests related to User object.
 * @author lavanya
 */
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user/{id}")
	public Optional<User> getUserConnected(@PathVariable ("id") int id) {
		
		Optional<User> user = userService.getUserById(id);
		
//		SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("id","password","encodedPassword");
//
//	       FilterProvider filterList = new SimpleFilterProvider().addFilter("userDataFiltered", myFilter);
//
//	       MappingJacksonValue userFiltered = new MappingJacksonValue(user);
//
//	       userFiltered.setFilters(filterList);
//
//       return userFiltered;
       
       return user;

	}
	
//	ResponseEntity est une classe qui hérite de HttpEntity,  qui permet de définir le code HTTP  à retourner. 
//	L'interêt de ResponseEntity est de nous donner la main pour personnaliser le code facilement.
	
//	Dans le cas où le produit ajouté est vide ou n'existe pas, nous retournons le code 204 No Content. Pour cela, la méthode noContent() est utilisée.  
//	Cette méthode est chainée avec la méthode build() qui construit le header et y ajoute le code choisi.
	
//	Nous ajoutons ensuite l'id du produit à l'URI à l'aide de la méthode buildAndExpand. Nous retrouvons l'id dans l'instance de Product que nous avons reçu : 
//	productAdded.getId().
//	Enfin, nous invoquons la méthode created de ResponseEntity, qui accepte comme argument l'URI de la ressource nouvellement créée et renvoie le code de statut 201.
	@PostMapping(value = "/save/user")
    public ResponseEntity<Void> ajouterProduit(@RequestBody User user) {
        User userAdded =  userService.saveUser(user);	
        if (userAdded == null)
            return ResponseEntity.noContent().build();

//        Creation de l'URI de l'utilisateur ajouté
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userAdded.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
