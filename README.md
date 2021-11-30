# Dark Souls
This is a text-based "rogue-like" game. It mimics the behaviours of a game named Dark Souls III.

## Purpose
It is to practice object-oriented programming and apply SOLID and other design principles.

## Gameplay
### Characters
**Player** `@`
- This is you playing the game!

**Undead** `u`
- Enemies that spawn from the cemetery and wander around the map. Follow and attack the player when detected. When they 
  are killed, player will gain 50 souls.

**Skeleton** `s`
- Enemies that wander around the map. Hold either Broadsword or Giant Axe. Follow and attack the player when detected. 
  When they are killed, enemies has a 50% probability to resurrect once and player will gain 250 souls.

**Mimic** `M`
- Enemies that spawns from opening a chest and wander around the map. Follow and attack the player when detected. When 
  they are killed, enemies drop 1-3 token of souls and player will gain 200 souls.

**Yhorm The Giant** `Y`
- The first boss in the game. Holds Yhorms Great Machete. Follows and attacks the player when detected. When the 
  hitpoints are below 50%, Ember Form is activated and will burn the surrounding area. When they are killed, enemies 
  drop cinders of the lord, and the player will gain 5000 souls.

**Aldrich The Devourer** `A`
- The second boss in the game. Holds Darkmoon Longbow. Follows and attacks the player when detected. When they are 
  killed, enemies drop cinder of the lord and player will gain 5000 souls.

### Weapons
**Broadsword** `b`
- A weapon that is initially held by the player. 
- Damage point: 30
- Hit rate: 80%
- Price: 500 souls

**Giant Axe** `g`
- A weapon that can be equipped by the player.
- Damage point: 50
- Hit rate: 80%
- Price: 1000 souls

**Storm Ruler** `7`
- A weapon that Yhorm The Giant is weak to.
- Damage point: 70
- Hit rate: 60%
- Price: 2000 souls

**Yhorms Great Machete** `y`
- A weapon that is held by Yhorm The Giant.
- Damage point: 95
- Hit rate: 60%

**Darkmoon Longbow** `d`
- A weapon that is held by Aldrich The Devourer.
- Damage point: 70
- Hit rate: 80%

### Items
**Cinder Of The Lord** `%`
- The corpse of the Lord Of Cinder. Can be traded to the Vendor to get the corresponding enemy’s weapon

**Token Of Soul** `$`
- Can be obtained when chests are opened or Mimic is killed. Drops when player dies and can be retrieved in the next 
  round.

### Ground
**Chest** `?`
- Opens and can either gives 1-3 token of soul or spawn Mimic when interacted by the player.

**Cemetery** `c`
- Has a 25% success rate to spawn undead every turn.

**Valley** `+`
- Instantly kills player when stepped.

**Wall** `#`
- Blocks thrown object and character from entering.

**Floor** `_`
- Only allows the player to enter. 

**Dirt** `.`
- All characters are allowed to enter. Can be burned by burn ground action.

**Burning Dirt** `v`
- Damages the player by 25 hit points. Burns for 3 rounds before turning back to Dirt.

**Bonfire** `B`
- The place for the player to rest. Refill player’s hit points to the maximum and Estus Flask to maximum charges.

**Fire Keeper** `F`
- The place for the player to trade. Can buy weapons and trade Cinder of The Lord.

### Maps
There are two different maps in this game:
1. Profane Capital
2. Anor Londo
