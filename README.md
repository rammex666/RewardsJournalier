# RewardsJournalier

## Description
RewardsJournalier is a Minecraft plugin that provides daily rewards to players. Players can receive free or premium rewards based on their playtime and permissions.

## Commands
- `/rd get free <player>`: Gives the specified player their free reward for the day.
- `/rd get premium <player>`: Gives the specified player their premium reward for the day, if they have the required permission.

## Configuration

```yaml
# Configuration for RewardsJournalier

# List of rewards for each day
rewards:
  days:
    1:
      - "give %player% minecraft:diamond 1"
    2:
      - "give %player% minecraft:emerald 1"
    # Add more days as needed

# List of premium rewards for each day
rewardspremium:
  days:
    1:
      - "give %player% minecraft:nether_star 1"
    2:
      - "give %player% minecraft:totem_of_undying 1"
    # Add more days as needed

# Messages
messages:
  already-claimed: "You have already claimed your reward for today."
  claim: "You have claimed your free reward!"
  claim-premium: "You have claimed your premium reward!"

# Permission for premium rewards
premiumperm: "rewardsjournalier.premium"