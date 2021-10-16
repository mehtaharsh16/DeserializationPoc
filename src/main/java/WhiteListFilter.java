import java.io.ObjectInputFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class WhiteListFilter implements ObjectInputFilter {
    private List<Class<?>> classes = new ArrayList<>();
    private Set<String> packages = new HashSet<>();

    public WhiteListFilter(List<Class<?>> allowedClasses) {
        this.classes = allowedClasses;
    }

    public WhiteListFilter(Set<String> allowedPackages) {
        this.packages = allowedPackages;
    }

    public WhiteListFilter(List<Class<?>> allowedClasses, Set<String> allowedPackages) {
        this.classes = allowedClasses;
        this.packages = allowedPackages;
    }

    @Override
    public Status checkInput(FilterInfo filterInfo) {
        if (filterInfo.serialClass() == null) {
            return Status.UNDECIDED;
        }

        if (classes.contains(filterInfo.serialClass()) ||
                packages.contains(filterInfo.serialClass().getPackageName()) ||
                filterInfo.serialClass().getModule().getName().equals("java.base")) {
            // validate that java.base doesn't contain vulnerable objects - if it does mark them in default blacklist or find other identifier to legitimate classes
            return Status.ALLOWED;
        } else {
            return Status.REJECTED;
        }
    }
}
