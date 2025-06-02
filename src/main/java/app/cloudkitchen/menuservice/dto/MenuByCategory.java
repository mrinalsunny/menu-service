package app.cloudkitchen.menuservice.dto;

import app.cloudkitchen.menuservice.document.CategoryDocument;
import app.cloudkitchen.menuservice.document.MenuItemDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuByCategory {
    private String categoryId;
    private List<MenuItemDocument> items;
    private Integer count;
    private CategoryDocument category;
}