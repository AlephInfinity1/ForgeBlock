src/main/java/alephinfinity1/forgeblock/client/EntityRenderHandler.java[36m:[m		 * Colour based on HP percen[1;31mtag[me
src/main/java/alephinfinity1/forgeblock/client/GuiMenu.java[36m:[m		double progress = skills.getProgressPercen[1;31mtag[me(SkillType.COMBAT);
src/main/java/alephinfinity1/forgeblock/item/FBPotionItem.java[36m:[m		if(stack.getTag() == null) return list; //If null [1;31mtag[m, return no effects.
src/main/java/alephinfinity1/forgeblock/item/swords/AspectOfTheEndItem.java[36m:[m			CompoundNBT [1;31mtag[m = aoteItem.getOrCreateTag();
src/main/java/alephinfinity1/forgeblock/item/swords/AspectOfTheEndItem.java[36m:[m			[1;31mtag[m.merge(living.getHeldItemMainhand().getOrCreateTag());
src/main/java/alephinfinity1/forgeblock/item/swords/AspectOfTheEnderItem.java[36m:[m				CompoundNBT [1;31mtag[m = aoteItem.getOrCreateTag();
src/main/java/alephinfinity1/forgeblock/item/swords/AspectOfTheEnderItem.java[36m:[m				[1;31mtag[m.merge(living.getHeldItemMainhand().getOrCreateTag());
src/main/java/alephinfinity1/forgeblock/item/swords/SilentDeathItem.java[36m:[m		CompoundNBT [1;31mtag[m = stack.getTag();
src/main/java/alephinfinity1/forgeblock/item/swords/SilentDeathItem.java[36m:[m		if (Objects.isNull([1;31mtag[m)) {
src/main/java/alephinfinity1/forgeblock/item/swords/SilentDeathItem.java[36m:[m			return [1;31mtag[m.getShort("Quality");
src/main/java/alephinfinity1/forgeblock/item/swords/SilentDeathItem.java[36m:[m		CompoundNBT [1;31mtag[m = stack.getTag();
src/main/java/alephinfinity1/forgeblock/item/swords/SilentDeathItem.java[36m:[m		if (Objects.isNull([1;31mtag[m)) {
src/main/java/alephinfinity1/forgeblock/item/swords/SilentDeathItem.java[36m:[m			return [1;31mtag[m.getShort("Floor");
src/main/java/alephinfinity1/forgeblock/misc/DamageHandler.java[36m:[m				double healthDiffPercen[1;31mtag[me = (victimHealth - damagerHealth) / damagerHealth * 100.0D;
src/main/java/alephinfinity1/forgeblock/misc/DamageHandler.java[36m:[m				double giantKillerMultiplier = healthDiffPercen[1;31mtag[me * giantKillerLevel * 0.001;
src/main/java/alephinfinity1/forgeblock/misc/capability/accessories/AccessoriesData.java[36m:[m    public void readNBT(INBT [1;31mtag[m) {
src/main/java/alephinfinity1/forgeblock/misc/capability/accessories/AccessoriesData.java[36m:[m        if ([1;31mtag[m instanceof CompoundNBT) {
src/main/java/alephinfinity1/forgeblock/misc/capability/accessories/AccessoriesData.java[36m:[m            ItemStackHelper.loadAllItems((CompoundNBT) [1;31mtag[m, ((AccessorInventory) contents).getInventoryContents());
src/main/java/alephinfinity1/forgeblock/misc/capability/skills/ISkills.java[36m:[m	 * Gets the double percen[1;31mtag[me progress, between 0-1, to the next level of a SkillType
src/main/java/alephinfinity1/forgeblock/misc/capability/skills/ISkills.java[36m:[m	 * @return double, percen[1;31mtag[me progress to the next level (Between 0.0d and 1.0d).
src/main/java/alephinfinity1/forgeblock/misc/capability/skills/ISkills.java[36m:[m	public double getProgressPercen[1;31mtag[me(SkillType skill);
src/main/java/alephinfinity1/forgeblock/misc/capability/skills/SkillData.java[36m:[m	public double getProgressPercen[1;31mtag[me() {
src/main/java/alephinfinity1/forgeblock/misc/capability/skills/SkillData.java[36m:[m		double prog = MathHelper.clamp(this.getProgressPercen[1;31mtag[me(), 0.0d, 1.0d - Float.MIN_NORMAL);
src/main/java/alephinfinity1/forgeblock/misc/capability/skills/Skills.java[36m:[m	 * Gets the percen[1;31mtag[me progress to the next level. E.g. 4.53%
src/main/java/alephinfinity1/forgeblock/misc/capability/skills/Skills.java[36m:[m	public double getProgressPercen[1;31mtag[me(SkillType skill) {
src/main/java/alephinfinity1/forgeblock/misc/capability/skills/Skills.java[36m:[m		return data.get(skill).getProgressPercen[1;31mtag[me();
src/main/java/alephinfinity1/forgeblock/mixin/MixinPlayerEntity.java[36m:[m                        double healthDiffPercen[1;31mtag[me = (victimHealth - damagerHealth) / damagerHealth * 100.0D;
src/main/java/alephinfinity1/forgeblock/mixin/MixinPlayerEntity.java[36m:[m                        double giantKillerMultiplier = healthDiffPercen[1;31mtag[me * giantKillerLevel * 0.001;
src/main/java/alephinfinity1/forgeblock/recipe/MultiShapedRecipe.java[36m:[m			throw new JsonParseException("Disallowed data [1;31mtag[m found");
