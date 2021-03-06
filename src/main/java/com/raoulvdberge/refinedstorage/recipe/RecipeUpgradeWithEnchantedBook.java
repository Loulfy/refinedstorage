package com.raoulvdberge.refinedstorage.recipe;

import com.raoulvdberge.refinedstorage.RS;
import com.raoulvdberge.refinedstorage.RSItems;
import com.raoulvdberge.refinedstorage.apiimpl.API;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

// MC JSON recipes don't like comparing to NBT, that's why we need a custom recipe class.
// We need to compare to NBT for the enchanted book.
public class RecipeUpgradeWithEnchantedBook extends ShapedRecipes {
    private ItemStack enchantedBook;

    public RecipeUpgradeWithEnchantedBook(String enchantmentId, int enchantmentLevel, int upgradeId) {
        super(RS.ID, 3, 3, NonNullList.from(Ingredient.EMPTY,
            Ingredient.fromStacks(new ItemStack(RSItems.QUARTZ_ENRICHED_IRON)),
            Ingredient.fromStacks(ItemEnchantedBook.getEnchantedItemStack(new EnchantmentData(Enchantment.getEnchantmentByLocation(enchantmentId), enchantmentLevel))),
            Ingredient.fromStacks(new ItemStack(RSItems.QUARTZ_ENRICHED_IRON)),
            Ingredient.fromStacks(new ItemStack(Blocks.BOOKSHELF)),
            Ingredient.fromStacks(new ItemStack(RSItems.UPGRADE)),
            Ingredient.fromStacks(new ItemStack(Blocks.BOOKSHELF)),
            Ingredient.fromStacks(new ItemStack(RSItems.QUARTZ_ENRICHED_IRON)),
            Ingredient.fromStacks(new ItemStack(RSItems.QUARTZ_ENRICHED_IRON)),
            Ingredient.fromStacks(new ItemStack(RSItems.QUARTZ_ENRICHED_IRON))
        ), new ItemStack(RSItems.UPGRADE, 1, upgradeId));

        this.enchantedBook = ItemEnchantedBook.getEnchantedItemStack(new EnchantmentData(Enchantment.getEnchantmentByLocation(enchantmentId), enchantmentLevel));
    }

    @Override
    public boolean matches(InventoryCrafting inv, World world) {
        return super.matches(inv, world) && API.instance().getComparer().isEqualNoQuantity(inv.getStackInSlot(1), enchantedBook);
    }
}
