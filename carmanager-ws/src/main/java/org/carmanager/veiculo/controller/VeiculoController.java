package org.carmanager.veiculo.controller;

import org.carmanager.veiculo.configuration.RestException;
import org.carmanager.veiculo.configuration.VeiculoNotFoundEx;
import org.carmanager.veiculo.repository.Veiculo;
import org.carmanager.veiculo.service.VeiculoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/veiculos")
public class VeiculoController {

    private final VeiculoService service;

    public VeiculoController(VeiculoService veiculoService) {
        this.service = veiculoService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Veiculo> getVeiculos() throws VeiculoNotFoundEx, RestException
    {
        try {
        final List<Veiculo> listVeiculos= service.getList();
        if (listVeiculos.size() == 0){
            throw new VeiculoNotFoundEx("Nenhum veículo localizado");
        }
            return listVeiculos;
        } catch (SQLException e) {
            throw new RestException("Erro ao recuperar todos os veiculos", e);
        }
    }

    @GET
    @Path("/find/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Veiculo>  getVeiculoByParam(@PathParam("param") String param)
    {
        return service.getfindVeiculo(param);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Veiculo getVeiculoById(@PathParam("id") int id) throws RestException, VeiculoNotFoundEx
    {
        try {
            final Veiculo veiculo = service.getVeiculo(id);
            if (veiculo == null){
                throw new VeiculoNotFoundEx("Nenhum veículo localizado com o id: "+ id);
            }
            return veiculo;

        } catch (SQLException e) {
            throw new RestException("Erro ao recuperar o veiculo com o id:"+id, e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addVeiculo(Veiculo veiculo)
            throws RestException {
        try {
            service.addVeiculo(veiculo);
        } catch (SQLException e) {
            throw new RestException("Erro ao adicionar o veículo ", e);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateVeiculo(Veiculo veiculo) throws RestException {
        try {
            service.updateVeiculo(veiculo);
        } catch (SQLException e) {
            throw new RestException("Erro ao atualizar o id = " + veiculo.getId(), e);
        }

    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    public void updateDataVeiculo(Veiculo veiculo)throws RestException {
        try {
            service.updateDataVeiculo(veiculo);
        } catch (SQLException e) {
            throw new RestException("Erro ao atualizar o id = " + veiculo.getId(), e);
        }

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteVeiculo(@PathParam("id") int id)
            throws RestException {
        try {
            service.deleteVeiculo(id);
        } catch (SQLException e) {
            throw new RestException("Erro ao excluir o id = " + id, e);
        }

    }

}
