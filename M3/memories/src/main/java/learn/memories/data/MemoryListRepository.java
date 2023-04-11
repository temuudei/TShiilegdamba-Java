package learn.memories.data;

import learn.memories.models.Memory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class MemoryListRepository implements MemoryRepository {
    @Override
    public List<Memory> findAll() throws DataAccessException {
        return null;
    }

    @Override
    public Memory findById(int memoryId) throws DataAccessException {
        return null;
    }

    @Override
    public List<Memory> findShareable(boolean shareable) throws DataAccessException {
        return null;
    }

    @Override
    public Memory add(Memory memory) throws DataAccessException {
        return null;
    }

    @Override
    public boolean update(Memory memory) throws DataAccessException {
        return false;
    }

    @Override
    public boolean deleteById(int memoryId) throws DataAccessException {
        return false;
    }
}
