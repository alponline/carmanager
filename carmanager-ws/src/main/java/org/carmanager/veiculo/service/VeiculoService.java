package org.carmanager.veiculo.service;

import org.carmanager.veiculo.repository.Veiculo;
import org.carmanager.veiculo.repository.VeiculoRepository;

import java.sql.SQLException;
import java.util.List;

public class VeiculoService {

    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.repository = veiculoRepository;
    }

    public List<Veiculo> getList() throws SQLException {

        return repository.getList();
    }

    public Veiculo getVeiculo(int id) throws SQLException {

        return repository.getVeiculo(id);
    }

    public void addVeiculo(Veiculo veiculo) throws SQLException {

        repository.add(veiculo);
    }

    public void updateVeiculo(Veiculo veiculo) throws SQLException {

        repository.update(veiculo);
    }

    public void deleteVeiculo(int id) throws SQLException {
        repository.delete(id);

    }

    public void updateDataVeiculo(Veiculo veiculo) throws SQLException {
        repository.updateData(veiculo);
    }

    public List<Veiculo>  getfindVeiculo(String param) {

        return repository.find(param);

    }
}
