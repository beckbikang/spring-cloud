package cn.beckbi.commonpool.simple;


import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2021-07-24 23:29
 */
public class TcpSessionFactory implements PooledObjectFactory<TcpSession> {

    private int objectCount = 0;

    /**
     * Creates an instance that can be served by the pool and wrap it in a
     * {@link PooledObject} to be managed by the pool.
     *
     * @return a {@code PooledObject} wrapping an instance that can be served by the pool
     *
     * @throws Exception if there is a problem creating a new instance,
     *    this will be propagated to the code requesting an object.
     */
    @Override
    public PooledObject<TcpSession> makeObject() throws Exception{
        ++ objectCount;
        return new DefaultPooledObject<TcpSession>(new TcpSession(true));
    }


    /**
     * Destroys an instance no longer needed by the pool, using the default (NORMAL)
     * DestroyMode.
     * <p>
     * It is important for implementations of this method to be aware that there
     * is no guarantee about what state {@code obj} will be in and the
     * implementation should be prepared to handle unexpected errors.
     * </p>
     * <p>
     * Also, an implementation must take in to consideration that instances lost
     * to the garbage collector may never be destroyed.
     * </p>
     *
     * @param p a {@code PooledObject} wrapping the instance to be destroyed
     *
     * @throws Exception should be avoided as it may be swallowed by
     *    the pool implementation.
     *
     * @see #validateObject
     * @see ObjectPool#invalidateObject
     */
    @Override
    public void destroyObject(PooledObject<TcpSession> p) throws Exception {
        --objectCount;
        p.getObject().setFlag(false);
    }



    @Override
    public boolean validateObject(PooledObject<TcpSession> p){
        return p.getObject().isFlag();
    }

    @Override
    public void activateObject(PooledObject<TcpSession> p) throws Exception {
        p.getObject().setFlag(true);
    }

    @Override
    public void passivateObject(PooledObject<TcpSession> p) throws Exception {
    }

    @Override
    public String toString(){
        return "objectCount:"+objectCount;
    }
}
