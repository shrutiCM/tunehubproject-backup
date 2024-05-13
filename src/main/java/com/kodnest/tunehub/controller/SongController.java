package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.service.SongService;

@Controller
public class SongController {

	@Autowired
	SongService songService;
	
	
	@PostMapping("/addsongs")
	public String addSong(@ModelAttribute Song song) {
	//to check a user with a email id is present or not
			
		String name=song.getName();
		boolean songExists=songService.songExists(name);
		
			if(songExists==false)
			{
			songService.saveSong(song);
			}else {
			System.out.println("duplicate song");
		}
			return "adminhome";

	}
	
	@GetMapping("/playsongs")
	public String playsongs(Model model) {
		
		boolean premium=false;
		if(premium) {
	List<Song> songslist=songService.fetchAllSongs();
	model.addAttribute("songs",songslist);
	
	System.out.println(songslist);
	
	return "viewsongs";
	}else {
		return "pay";
	}
	}
	
	@GetMapping("/viewsongs")
	public String viewsongs(Model model) {
		
		
	List<Song> songslist=songService.fetchAllSongs();
	model.addAttribute("songs",songslist);
	
	System.out.println(songslist);
	
	return "viewsongs";
	
	
	}
	
	
	
}
