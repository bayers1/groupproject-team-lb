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

