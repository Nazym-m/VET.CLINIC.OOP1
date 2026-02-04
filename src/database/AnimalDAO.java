package database;

import model.Animal;
import model.Dog;
import model.Cat;
import exception.AnimalAgeException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    public boolean insertAnimal(Animal animal) {
        String sql = "INSERT INTO animal (name, species, age, owner_name, breed, is_indoor, is_vaccinated) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, animal.getName());

            if (animal instanceof Dog) {
                statement.setString(2, "Dog");
            } else if (animal instanceof Cat) {
                statement.setString(2, "Cat");
            }

            statement.setInt(3, animal.getAge());
            statement.setString(4, animal.getOwnerName());

            if (animal instanceof Dog) {
                Dog dog = (Dog) animal;
                statement.setString(5, dog.getBreed());
                statement.setBoolean(6, false); // Dog is outdoor
                statement.setBoolean(7, false); // Not vaccinated by default
            } else if (animal instanceof Cat) {
                Cat cat = (Cat) animal;
                statement.setString(5, null); // Cat has no breed
                statement.setBoolean(6, cat.isIndoor());
                statement.setBoolean(7, false); // Not vaccinated by default
            }

            int rowsInserted = statement.executeUpdate();
            statement.close();

            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("❌ Insert failed: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public List<Animal> getAllAnimals() {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM animal ORDER BY animal_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return animals;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Animal animal = extractAnimalFromResultSet(resultSet);
                if (animal != null) {
                    animals.add(animal);
                }
            }

            resultSet.close();
            statement.close();

        } catch (SQLException | AnimalAgeException e) {
            System.out.println("❌ Select failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return animals;
    }

    public Animal getAnimalById(int animalId) {
        String sql = "SELECT * FROM animal WHERE animal_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, animalId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return extractAnimalFromResultSet(resultSet);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException | AnimalAgeException e) {
            System.out.println("❌ Get by ID failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return null;
    }

    public boolean updateAnimal(Animal animal) {
        String sql = "UPDATE animal SET name = ?, age = ?, owner_name = ?, breed = ?, is_indoor = ?, is_vaccinated = ? " +
                "WHERE animal_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, animal.getName());
            statement.setInt(2, animal.getAge());
            statement.setString(3, animal.getOwnerName());

            if (animal instanceof Dog) {
                Dog dog = (Dog) animal;
                statement.setString(4, dog.getBreed());
                statement.setBoolean(5, false); // Dog is outdoor
                statement.setBoolean(6, false); // Default vaccination for dog
            } else if (animal instanceof Cat) {
                Cat cat = (Cat) animal;
                statement.setString(4, null); // Cat has no breed
                statement.setBoolean(5, cat.isIndoor());
                // If cat is indoor, consider it vaccinated
                statement.setBoolean(6, cat.isIndoor());
            }

            statement.setInt(7, animal.getId());

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("❌ Update failed: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public boolean deleteAnimal(int animalId) {
        String sql = "DELETE FROM animal WHERE animal_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, animalId);

            int rowsDeleted = statement.executeUpdate();
            statement.close();

            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.out.println("❌ Delete failed: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public List<Animal> searchByName(String searchTerm) {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM animal WHERE name ILIKE ? ORDER BY name";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return animals;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + searchTerm + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Animal animal = extractAnimalFromResultSet(resultSet);
                if (animal != null) {
                    animals.add(animal);
                }
            }

            resultSet.close();
            statement.close();

        } catch (SQLException | AnimalAgeException e) {
            System.out.println("❌ Search failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return animals;
    }

    public List<Animal> searchByOwner(String ownerName) {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM animal WHERE owner_name ILIKE ? ORDER BY owner_name";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return animals;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + ownerName + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Animal animal = extractAnimalFromResultSet(resultSet);
                if (animal != null) {
                    animals.add(animal);
                }
            }

            resultSet.close();
            statement.close();

        } catch (SQLException | AnimalAgeException e) {
            System.out.println("❌ Search failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return animals;
    }

    public int countByType(String animalType) {
        String sql = "SELECT COUNT(*) FROM animal WHERE species = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return 0;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, animalType); // "Dog" or "Cat"

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();
            System.out.println("✅ Found " + animals.size() + " animals between ages " + minAge + " and " + maxAge);
        } catch (SQLException | AnimalAgeException e) {
            System.out.println("❌ Search by age range failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return animals;
    }

    public List<Animal> searchByMinAge(int minAge) {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM animal WHERE age >= ? ORDER BY age DESC";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return animals;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, minAge);
            ResultSet resultSet = statement.executeQuery();

        } catch (SQLException e) {
            System.out.println("❌ Count failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return 0;
    }

    private Animal extractAnimalFromResultSet(ResultSet resultSet) throws SQLException, AnimalAgeException {
        int id = resultSet.getInt("animal_id");
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        String owner = resultSet.getString("owner_name");
        String species = resultSet.getString("species");
        String breed = resultSet.getString("breed");
        boolean isIndoor = resultSet.getBoolean("is_indoor");

        if (species.equalsIgnoreCase("dog")) {
            Dog dog = new Dog(id, name, age, owner, breed != null ? breed : "Unknown");
            return dog;
        } else if (species.equalsIgnoreCase("cat")) {
            Cat cat = new Cat(id, name, age, owner, isIndoor);
            return cat;
        }

        return null;
    }

    public void showStatistics() {
        String sql = "SELECT " +
                "COUNT(*) as total, " +
                "SUM(CASE WHEN species = 'Dog' THEN 1 ELSE 0 END) as dogs, " +
                "SUM(CASE WHEN species = 'Cat' THEN 1 ELSE 0 END) as cats, " +
                "SUM(CASE WHEN is_vaccinated THEN 1 ELSE 0 END) as vaccinated " +
                "FROM animal";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                int total = resultSet.getInt("total");
                int dogs = resultSet.getInt("dogs");
                int cats = resultSet.getInt("cats");
                int vaccinated = resultSet.getInt("vaccinated");

                System.out.println("\n--- CLINIC STATISTICS ---");
                System.out.println("Total animals: " + total);
                System.out.println("Dogs: " + dogs);
                System.out.println("Cats: " + cats);
                System.out.println("Vaccinated: " + vaccinated);

                if (total > 0) {
                    double vaccinationRate = (vaccinated * 100.0) / total;
                    System.out.printf("Vaccination rate: %.1f%%\n", vaccinationRate);
                }
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("❌ Statistics failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }
}