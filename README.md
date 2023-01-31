The feature that I intend to implement within project is the animations of the four playable characters. The way I intend to implement this feature is by creating 
duplicates of the four character's images and editing their wings to be pointed downwards. Afterwards, I will combine the original and edited version together into one
.GIF file for each character. I came to this conclusion upon realizing that GImage supports the .GIF format, so I can simply edit the code to have our game insert the 
.GIF of the character while making next to no other alterations to the rest of the program. The change to this part of the code would be rather simple, as illustrated 
in the psuedocode below.

	acquire player's selected type as prereq
	initialize filetype
	if type is one of the four types
		add type to filetype  
		set scene based on type  
	Endif
	add "Dragon.gif" to filetype (was originally 'add "Dragon" and image extension to filetype')
	generate image using completed filetype
	set image's size and location
	add image to the board

True to my expectations, I was able to implement the .gif files into the program and display them within the main gameplay. Performance within the gameplay itself was also not impeded as a result, much to my surprise. However, there was a minor issue with the visuals of the gifs themselves being a bit... distorted, which I found quite odd because the gifs themselves did not appear to be erroneous on my own system or when previewing them on GitHub or Eclipse. Nevertheless, I still consider this implementation to be a success. 
