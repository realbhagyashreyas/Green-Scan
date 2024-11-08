package com.example.ecofriendlyscanner;

import com.google.gson.annotations.SerializedName;

public class ProductResponse {
    private int status;
    private Product product;

    public int getStatus() {
        return status;
    }

    public Product getProduct() {
        return product;
    }

    public static class Product {

        private String product_name;
        private String brands;
        private String packaging;
        private String ingredients_text;

        @SerializedName("nutrition_grade_fr")
        private String nutritionGrade;

        @SerializedName("image_url")
        private String imageUrl;

        private String ecoscore_grade;

        // Nutrition facts
        @SerializedName("nutriments")
        private Nutriments nutriments;

        public String getProductName() {
            return product_name;
        }

        public String getBrands() {
            return brands;
        }

        public String getPackaging() {
            return packaging;
        }

        public String getIngredients() {
            return ingredients_text;
        }

        public String getEcoScore() {
            return ecoscore_grade;
        }

        public String getNutriScore() {
            return nutritionGrade;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public Nutriments getNutriments() {
            return nutriments;
        }

        public static class Nutriments {
            @SerializedName("energy_100g")
            private float energy;  // Energy in kJ per 100g

            @SerializedName("fat_100g")
            private float fat;  // Fat in grams per 100g

            @SerializedName("saturated-fat_100g")
            private float saturatedFat;  // Saturated fat in grams per 100g

            @SerializedName("carbohydrates_100g")
            private float carbohydrates;  // Carbohydrates in grams per 100g

            @SerializedName("sugars_100g")
            private float sugars;  // Sugars in grams per 100g

            @SerializedName("fiber_100g")
            private float fiber;  // Fiber in grams per 100g

            @SerializedName("proteins_100g")
            private float proteins;  // Proteins in grams per 100g

            @SerializedName("salt_100g")
            private float salt;  // Salt in grams per 100g

            public float getEnergy() {
                return energy;
            }

            public float getFat() {
                return fat;
            }

            public float getSaturatedFat() {
                return saturatedFat;
            }

            public float getCarbohydrates() {
                return carbohydrates;
            }

            public float getSugars() {
                return sugars;
            }

            public float getFiber() {
                return fiber;
            }

            public float getProteins() {
                return proteins;
            }

            public float getSalt() {
                return salt;
            }
        }
    }
}
