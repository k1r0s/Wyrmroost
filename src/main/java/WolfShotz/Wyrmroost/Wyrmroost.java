package WolfShotz.Wyrmroost;

import WolfShotz.Wyrmroost.util.proxy.ClientProxy;
import WolfShotz.Wyrmroost.util.proxy.IProxy;
import WolfShotz.Wyrmroost.util.proxy.ServerProxy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("wyrmroost")
public class Wyrmroost
{
    public static final Logger L = LogManager.getLogger();
    public static final IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
    public static final String modID = "wyrmroost";

    public Wyrmroost() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Could be handled on the proxy...?
//        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

    }

    private void setup(final FMLCommonSetupEvent event) {

        L.debug("setup started");
    }
}
