package net.rudahee.metallics_arts.modules.blocks.alloy_furnace;


public class AlloyFurnaceTileEntity  {
/*
    private static final int[] SLOTS_FOR_UP = new int[]{0,1,2,3};
    private static final int[] SLOTS_FOR_DOWN = new int[]{5};
    private static final int[] SLOTS_FOR_SIDES = new int[]{4};

    protected NonNullList<ItemStack> items = NonNullList.withSize(5, ItemStack.EMPTY);

    private final AlloyFurnaceData data = new AlloyFurnaceData();
    public final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    private static ItemStack[] itemInSlot = new ItemStack[6];

    public AlloyFurnaceTileEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
        super(p_155228_, p_155229_, p_155230_);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        data.readNBTData(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        data.updateNBTData(nbt);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(6) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        return (Items.IRON_INGOT == stack.getItem()||
                                Items.GOLD_INGOT == stack.getItem()||
                                Items.COAL == stack.getItem() ||
                                ModItems.ITEM_METAL_INGOT.values().stream().anyMatch(m-> m == stack.getItem())||
                                ModItems.ITEM_GEMS_BASE.values().stream().anyMatch(m -> m == stack.getItem()));
                    case 5:
                        if (recipe.isPresent()) {
                            return (recipe.get().getResultItem().getItem() == stack.getItem());
                        } else {
                            return false;
                        }
                    case 4:
                        return (Items.COAL_BLOCK.asItem() == stack.getItem()||
                                Items.COAL.asItem() == stack.getItem()||
                                Items.BLAZE_ROD.asItem() == stack.getItem()||
                                Items.DRIED_KELP_BLOCK.asItem() == stack.getItem()||
                                Items.LAVA_BUCKET.asItem() == stack.getItem());
                        // return Items.COAL_BLOCK.getItem() == stack.getItem() ? true : false;
                    default:
                        return false;
                }
            }

            @Override
            public int getSlotLimit(int slot) {
                return 64; // Slot limits
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
                    return stack; // Only insert correct item
                }

                AlloyFurnaceTileEntity.itemInSlot[slot] = stack;

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }

    private void craftTheItem(ItemStack output) {
        itemHandler.extractItem(0, 1, false);
        itemHandler.extractItem(1, 1, false);
        itemHandler.extractItem(2, 1, false);
        itemHandler.extractItem(3, 1, false);

        itemHandler.insertItem(5, output, false);
    }

    @Override
    public void tick() {

        if (!this.level.isClientSide()) {
            if(cookingRecipe() == 1) {
                this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(AlloyFurnaceBlock.LIT, Boolean.valueOf(this.isLit())), 3);

            } else {
                this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(AlloyFurnaceBlock.LIT, Boolean.valueOf(this.isLit())), 3);

            }
        }


    }
    private boolean checkFuel() {
        Inventory inventoryFuel = new Inventory(1);
        inventoryFuel.setItem(0, itemHandler.getStackInSlot(4));

        return !inventoryFuel.isEmpty();
    }

    private boolean checkAir(Inventory inventoryRecipe) {
        boolean containAir = false;
        for (int i = 0; i < inventoryRecipe.getContainerSize(); i++) {
            if (inventoryRecipe.getItem(i).getItem().equals(Items.AIR)
                    || inventoryRecipe.getItem(i).isEmpty()
                    || inventoryRecipe.getItem(i).getItem() == null) {

                containAir = true;
            }
        }
        return  containAir;

    }

    Optional<AlloyFurnaceRecipe> recipe = Optional.empty();

    private int cookingRecipe() {

        // Create inventory with X slots
        Inventory inventoryRecipe = new Inventory(4);

        // For each slot put in inventory what we do here.
        for (int i = 0; i < 4; i++) {
            inventoryRecipe.setItem(i, itemHandler.getStackInSlot(i));
        }


        recipe = this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.ALLOY_FURNACE_RECIPE, inventoryRecipe, level);

        boolean isResultItemValid = false;

        if (recipe.isPresent()) {
            if (recipe.get().getResultItem().getItem() == itemHandler.getStackInSlot(5).getItem() || itemHandler.getStackInSlot(5).isEmpty()) {
                isResultItemValid = true;
            }
        }


        if (checkAir(inventoryRecipe)) {
            recipe = Optional.empty();
        }
        if (itemHandler.getStackInSlot(5).getCount() >= 64) {
            data.isCrafting = 0;
        }

        // If not crafting anything
        if (data.isCrafting == 0) {
            // If recipe exists and is lit, we start to craft the item.
            if (recipe.isPresent()) {
                if  (!isResultItemValid || itemHandler.getStackInSlot(5).getCount() >= 64) {
                    if (data.actualFuelBurning > 0) {
                        data.actualFuelBurning--;
                    }
                    data.actualTimeToActualRecipe = 0;
                } else {
                    if (isLit()) {
                        AlloyFurnaceRecipe presentRecipe = recipe.get();
                        data.isCrafting = 1;

                        data.maxTimeToActualRecipe = Arrays.stream(MetalBurningRecipeData.values())
                                .filter(m -> m.getItem().getDescriptionId().equals(presentRecipe.getResultItem().getDescriptionId()))
                                .findFirst().get()
                                .getTicksToCompleteBurning();

                        data.actualTimeToActualRecipe = data.maxTimeToActualRecipe;
                        data.actualFuelBurning--;
                    } else {

                        // If not lit, we check fuel, and we have fuel, get 1 and lit.
                        if (checkFuel()) {
                            refillLit();
                        } else {
                            data.actualTimeToActualRecipe = -1;
                            data.isCrafting = 0;
                        }
                    }
                }
            } else {
                if (isLit()) {
                    data.actualFuelBurning--;
                }
            }
        } else {

            if (!this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.ALLOY_FURNACE_RECIPE, inventoryRecipe, level).isPresent()) {
                data.isCrafting = 0;
                data.actualTimeToActualRecipe = -1;
                recipe = Optional.empty();
            }

            if (checkAir(inventoryRecipe)) {
                data.isCrafting = 0;
                data.actualTimeToActualRecipe = -1;
            }

            if (isLit()) {
                data.actualFuelBurning--;
                data.actualTimeToActualRecipe = (data.actualTimeToActualRecipe <= 0) ? data.actualTimeToActualRecipe : --data.actualTimeToActualRecipe;

                if (isCompleteCrafting() && !checkAir(inventoryRecipe)) {
                    if (!this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.ALLOY_FURNACE_RECIPE, inventoryRecipe, level).isPresent()) {
                        recipe = Optional.empty();
                    } else {
                        craftTheItem(recipe.get().getResultItem());
                    }

                    data.actualTimeToActualRecipe = -1;
                    data.isCrafting = 0;



                } else {
                    if (!isLit()) {
                        if (checkFuel()) {
                            refillLit();
                        } else {
                            data.actualTimeToActualRecipe = -1;
                            data.isCrafting = 0;
                        }
                    }
                }
            } else {
                if (checkFuel()) {
                    refillLit();
                } else {
                    data.actualTimeToActualRecipe = -1;
                    data.isCrafting = 0;


                }
            }
        }

        return data.isCrafting;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundTag nbtTagCompound = new CompoundTag();
        nbtTagCompound = save(nbtTagCompound);
        int tileEntityType = getType().hashCode();  // arbitrary number; only used for vanilla TileEntities.  You can use it, or not, as you want.
        return new SUpdateTileEntityPacket(getBlockPos(), tileEntityType, nbtTagCompound);
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundTag tag) {
        super.handleUpdateTag(state, tag);
        data.updateNBTData(tag);
    }

    @Override
    public CompoundTag getUpdateTag() {
        super.getUpdateTag();

        CompoundTag tag = new CompoundTag();
        tag = save(tag);
        return tag;
    }

    public boolean isLit() {
        return data.actualFuelBurning >= 0;
    }

    public boolean isCompleteCrafting() {
        return data.actualTimeToActualRecipe <= 0;
    }

    public void refillLit() {
        Item fuelItem = itemHandler.extractItem(4, 1, false).getItem();

        data.maxFuelBurning = Arrays.stream(FuelsTime.values())
                .filter(f -> f.getItem().equals(fuelItem.asItem()))
                .findFirst().get()
                .getTicksBurning();


        data.actualFuelBurning = data.maxFuelBurning;
    }

    public static ItemStack getItemInSlot(int slot) {
        return itemInSlot[slot];
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        BlockState blockState = getBlockState();
        this.load(blockState, pkt.getTag());   // read from the nbt in the packet

    }

    @Nullable
    @Override
    public AlloyFurnaceContainer createMenu(int windowId, Inventory inventory, Player entity) {
        return AlloyFurnaceContainer.createContainerInServerSide(windowId, this.getBlockPos(), inventory, this.data);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("screen.metallics_arts.alloy_furnace");
    }
*/
}

