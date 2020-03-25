public class GenerateNPC {
    public static void main(String[] args) {
        for (int i = 1; i < 11; i++) {
            NonPlayerCharacter npc = new NonPlayerCharacter();
            System.out.println(i +". " + npc.getFirstName() + " is a/an " + npc.getRace().toString() + " with " + npc.getHairstyle() + ". They carry " + npc.getAccessories() + ".");
        }
    }
}
