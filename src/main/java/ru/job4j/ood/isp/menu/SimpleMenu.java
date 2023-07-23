package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {
    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actDlgt) {
        boolean rsl = false;
        if (parentName.equals(ROOT)) {
            SimpleMenuItem simpleMenuItem = new SimpleMenuItem(childName, actDlgt);
            rootElements.add(simpleMenuItem);
            rsl = true;
        } else {
            if (findItem(parentName).isPresent()) {
                ItemInfo itemInfoParent = findItem(parentName).get();
                SimpleMenuItem simpleMenuItem = new SimpleMenuItem(childName, actDlgt);
                itemInfoParent.menuItem.getChildren().add(simpleMenuItem);
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<MenuItemInfo> rsl = Optional.empty();
        if (findItem(itemName).isPresent()) {
            ItemInfo itemInfo = findItem(itemName).get();
            MenuItemInfo menuItemInfo = new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
            rsl = Optional.of(menuItemInfo);
        }
        return rsl;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        Iterator<MenuItemInfo> itr = new Iterator<MenuItemInfo>() {
            DFSIterator dfsIterator = new DFSIterator();
            @Override
            public boolean hasNext() {
                return dfsIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo itemInfo = dfsIterator.next();
                return new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
            }
        };
        return itr;
    }

    private Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> rsl = Optional.empty();
        DFSIterator dfsI = new DFSIterator();
        while (dfsI.hasNext()) {
            ItemInfo itemInfo = dfsI.next();
            if (itemInfo.menuItem.getName().equals(name)) {
                rsl = Optional.of(itemInfo);
                break;
            }
        }
        return rsl;
    }

    private static class SimpleMenuItem implements MenuItem {
        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDlgt;

        public SimpleMenuItem(String name, ActionDelegate actionDlgt) {
            this.name = name;
            this.actionDlgt = actionDlgt;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDlgt;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {
        Deque<MenuItem> stack = new LinkedList<>();
        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class ItemInfo {
        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}

