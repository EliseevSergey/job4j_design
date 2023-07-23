package ru.job4j.ood.isp.menu;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public interface Menu extends Iterable<Menu.MenuItemInfo> {
    String ROOT = "SUMMIT";

    boolean add(String parentName, String childName, ActionDelegate actDlgt);
    Optional<MenuItemInfo> select(String itemName);

    class MenuItemInfo {
        private final String name;
        private final List<String> children;
        private final ActionDelegate actDlgt;
        private final String num;

        public MenuItemInfo(MenuItem mI, String num) {
            this.name = mI.getName();
            this.children = mI.getChildren().stream()
                    .map(MenuItem::getName)
                    .collect(Collectors.toList());
            this.actDlgt = mI.getActionDelegate();
            this.num = num;
        }

        public MenuItemInfo(String name, List<String> children, ActionDelegate actDlgt, String num) {
            this.name = name;
            this.children = children;
            this.actDlgt = actDlgt;
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public List<String> getChildren() {
            return children;
        }

        public ActionDelegate getActDlgt() {
            return actDlgt;
        }

        public String getNum() {
            return num;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)  {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MenuItemInfo that = (MenuItemInfo) o;
            return Objects.equals(name, that.name) && Objects.equals(children, that.children) && Objects.equals(num, that.num);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, children, num);
        }

        @Override
        public String toString() {
            return "MenuItemInfo{" + "name='" + name + '\'' + ", children="
                    + children + ", num='" + num + '\'' + '}';
        }
    }
}
