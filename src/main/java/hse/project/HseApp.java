package hse.project;

import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.compute.DiskInstanceView;
import com.microsoft.azure.management.compute.InstanceViewStatus;
import com.microsoft.azure.management.compute.VirtualMachine;
import com.microsoft.azure.management.compute.VirtualMachineSizes;
import com.microsoft.azure.management.network.Network;
import com.microsoft.azure.management.network.NetworkInterface;
import com.microsoft.azure.management.network.PublicIPAddress;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import com.microsoft.rest.LogLevel;
import hse.project.utils.AzureAuth;
import hse.project.utils.AzureBillingUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.io.File;

@EnableWebSecurity
@SpringBootApplication
public class HseApp {

    public static void main(String[] args) {
        try {
            final File credFile = new File("M:\\java HW\\hseapp\\src\\main\\resources\\azureauth.properties");
            Azure azure = Azure.configure()
                .withLogLevel(LogLevel.BASIC)
                .authenticate(credFile)
                .withDefaultSubscription();
            AzureAuth.setAzure(azure);
    
            /*PublicIPAddress publicIPAddress = azure.publicIPAddresses()
                .define("myPublicIP")
                .withRegion(Region.US_EAST)
                .withExistingResourceGroup("test-machine-1_group")
                .withDynamicIP()
                .create();
    
            System.out.println("Creating virtual network...");
            Network network = azure.networks()
                .define("myVN")
                .withRegion(Region.US_EAST)
                .withExistingResourceGroup("test-machine-1_group")
                .withAddressSpace("10.0.0.0/16")
                .withSubnet("mySubnet","10.0.0.0/24")
                .create();
    
            NetworkInterface networkInterface = azure.networkInterfaces()
                .define("myNIC")
                .withRegion(Region.US_EAST)
                .withExistingResourceGroup("test-machine-1_group")
                .withExistingPrimaryNetwork(network)
                .withSubnet("mySubnet")
                .withPrimaryPrivateIPAddressDynamic()
                .withExistingPrimaryPublicIPAddress(publicIPAddress)
                .create();
    
            //VirtualMachineSizes sizes = azure.;
            //System.out.println(sizes.listByRegion(Region.US_EAST));
            com.microsoft.azure.management.compute.VirtualMachine virtualMachine = azure.virtualMachines()
                .define("test_app2")
                .withRegion(Region.US_EAST)
                .withExistingResourceGroup("test-machine-1_group")
                .withExistingPrimaryNetworkInterface(networkInterface)
                .withLatestWindowsImage("MicrosoftWindowsServer", "WindowsServer", "2016-Datacenter")
                .withAdminUsername("login")
                .withAdminPassword("Password1234")
                .withComputerName("computerApp")
                .withSize("Standard_B1ls")
                .create();*/
    
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        SpringApplication.run(HseApp.class, args);
        System.out.println("Hello ");
        try {
            AzureBillingUtils.billingProcess();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
