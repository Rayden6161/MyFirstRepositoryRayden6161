import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public interface Filter {
    ArrayList<Proizvod>filter(ArrayList<Proizvod> proizvodi, HashMap<String,Object> args  );
}

