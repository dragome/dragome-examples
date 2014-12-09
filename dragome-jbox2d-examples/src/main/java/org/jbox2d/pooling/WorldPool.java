/*******************************************************************************
 * Copyright (c) 2011, Daniel Murphy
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL DANIEL MURPHY BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/
/**
 * Created at 3:26:14 AM Jan 11, 2011
 */
package org.jbox2d.pooling;

import java.util.HashMap;

import org.jbox2d.collision.AABB;
import org.jbox2d.collision.Collision;
import org.jbox2d.collision.Distance;
import org.jbox2d.collision.TimeOfImpact;
import org.jbox2d.common.Mat22;
import org.jbox2d.common.Settings;
import org.jbox2d.common.Vec2;
import org.jbox2d.common.Vec3;
import org.jbox2d.pooling.PoolingStack.PoolContainer;

/**
 * Provides object pooling for all objects used in the engine.  Objects
 * retrieved from here should only be used temporarily, and then pushed back (with
 * the exception of arrays).
 * @author Daniel Murphy
 */
public class WorldPool
{
	class PoolingStackVec2 extends PoolingStack<Vec2>
	{

		public PoolingStackVec2(int argStackSize)
		{
			super(Vec2.class, argStackSize);
			// TODO Auto-generated constructor stub
		}
	};
	class PoolingStackVec3 extends PoolingStack<Vec3>
	{

		public PoolingStackVec3(int argStackSize)
		{
			super(Vec3.class, argStackSize);
			// TODO Auto-generated constructor stub
		}
	};
	class PoolingStackMat22 extends PoolingStack<Mat22>
	{

		public PoolingStackMat22(int argStackSize)
		{
			super(Mat22.class, argStackSize);
			// TODO Auto-generated constructor stub
		}
	};
	class PoolingStackAABB extends PoolingStack<AABB>
	{

		public PoolingStackAABB(int argStackSize)
		{
			super(AABB.class, argStackSize);
			// TODO Auto-generated constructor stub
		}
	};

	private PoolingStack<Vec2> vecs;
	private PoolingStack<Vec3> vec3s;
	private PoolingStack<Mat22> mats;
	private PoolingStack<AABB> aabbs;

	private HashMap<Integer, float[]> afloats= new HashMap<Integer, float[]>();
	private HashMap<Integer, int[]> aints= new HashMap<Integer, int[]>();
	private HashMap<Integer, Vec2[]> avecs= new HashMap<Integer, Vec2[]>();

	private PolygonContactStack pcstack= new PolygonContactStack(this);
	private CircleContactStack ccstack= new CircleContactStack(this);
	private PolyCircleContactStack cpstack= new PolyCircleContactStack(this);

	private Collision collision;
	private TimeOfImpact toi;
	private Distance dist;

	public WorldPool(int argSize)
	{
		vecs= new PoolingStackVec2(argSize);
		vec3s= new PoolingStackVec3(argSize);
		mats= new PoolingStackMat22(argSize);
		aabbs= new PoolingStackAABB(argSize);

		dist= new Distance();
		collision= new Collision(this);
		toi= new TimeOfImpact(this);
	}

	public PolygonContactStack getPolyContactStack()
	{
		return pcstack;
	}

	public CircleContactStack getCircleContactStack()
	{
		return ccstack;
	}

	public PolyCircleContactStack getPolyCircleContactStack()
	{
		return cpstack;
	}

	public PoolingStack<Vec2> getVec2Stack()
	{
		return vecs;
	}

	/**
	 * @return
	 * @see org.jbox2d.pooling.PoolingStack#get()
	 */
	public Vec2 popVec2()
	{
		if (!Settings.POOLING)
		{
			return new Vec2();
		}
		return vecs.pop();
	}

	/**
	 * @param argNum
	 * @return
	 * @see org.jbox2d.pooling.PoolingStack#get(int)
	 */
	public PoolContainer<Vec2> popVec2(int argNum)
	{
		if (!Settings.POOLING)
		{
			PoolContainer<Vec2> pc= new PoolContainer<Vec2>();
			Vec2[] ray= new Vec2[PoolContainer.MAX_MEMBERS];
			for (int i= 0; i < argNum; i++)
			{
				ray[i]= new Vec2();
			}
			pc.populate(ray);
			return pc;
		}
		return vecs.pop(argNum);
	}

	/**
	 * @param argNum
	 * @see org.jbox2d.pooling.PoolingStack#reclaim(int)
	 */
	public void pushVec2(int argNum)
	{
		if (!Settings.POOLING)
		{
			return;
		}
		vecs.push(argNum);
	}

	public PoolingStack<Vec3> getVec3Stack()
	{
		return vec3s;
	}

	public Vec3 popVec3()
	{
		if (!Settings.POOLING)
		{
			return new Vec3();
		}
		return vec3s.pop();
	}

	public PoolContainer<Vec3> popVec3(int argNum)
	{
		if (!Settings.POOLING)
		{
			PoolContainer<Vec3> pc= new PoolContainer<Vec3>();
			Vec3[] ray= new Vec3[PoolContainer.MAX_MEMBERS];
			for (int i= 0; i < argNum; i++)
			{
				ray[i]= new Vec3();
			}
			pc.populate(ray);
			return pc;
		}
		return vec3s.pop(argNum);
	}

	public void pushVec3(int argNum)
	{
		if (!Settings.POOLING)
		{
			return;
		}
		vec3s.push(argNum);
	}

	public PoolingStack<Mat22> getMat22Stack()
	{
		return mats;
	}

	public Mat22 popMat22()
	{
		if (!Settings.POOLING)
		{
			return new Mat22();
		}
		return mats.pop();
	}

	public PoolContainer<Mat22> popMat22(int argNum)
	{
		if (!Settings.POOLING)
		{
			PoolContainer<Mat22> pc= new PoolContainer<Mat22>();
			Mat22[] ray= new Mat22[PoolContainer.MAX_MEMBERS];
			for (int i= 0; i < argNum; i++)
			{
				ray[i]= new Mat22();
			}
			pc.populate(ray);
			return pc;
		}
		return mats.pop(argNum);
	}

	public void pushMat22(int argNum)
	{
		if (!Settings.POOLING)
		{
			return;
		}
		mats.push(argNum);
	}

	public PoolingStack<AABB> getAABBStack()
	{
		return aabbs;
	}

	public AABB popAABB()
	{
		if (!Settings.POOLING)
		{
			return new AABB();
		}
		return aabbs.pop();
	}

	public PoolContainer<AABB> popAABB(int argNum)
	{
		if (!Settings.POOLING)
		{
			PoolContainer<AABB> pc= new PoolContainer<AABB>();
			AABB[] ray= new AABB[PoolContainer.MAX_MEMBERS];
			for (int i= 0; i < argNum; i++)
			{
				ray[i]= new AABB();
			}
			pc.populate(ray);
			return pc;
		}
		return aabbs.pop(argNum);
	}

	public void pushAABB(int argNum)
	{
		if (!Settings.POOLING)
		{
			return;
		}
		aabbs.push(argNum);
	}

	public Collision getCollision()
	{
		return collision;
	}

	public TimeOfImpact getTimeOfImpact()
	{
		return toi;
	}

	public Distance getDistance()
	{
		return dist;
	}

	public float[] getFloatArray(int argLength)
	{
		if (!afloats.containsKey(argLength))
		{
			afloats.put(argLength, new float[argLength]);
		}

		assert (afloats.get(argLength).length == argLength) : "Array not built with correct length";
		return afloats.get(argLength);
	}

	public int[] getIntArray(int argLength)
	{
		if (!aints.containsKey(argLength))
		{
			aints.put(argLength, new int[argLength]);
		}

		assert (aints.get(argLength).length == argLength) : "Array not built with correct length";
		return aints.get(argLength);
	}

	public Vec2[] getVec2Array(int argLength)
	{
		if (!avecs.containsKey(argLength))
		{
			Vec2[] ray= new Vec2[argLength];
			for (int i= 0; i < argLength; i++)
			{
				ray[i]= new Vec2();
			}
			avecs.put(argLength, ray);
		}

		assert (avecs.get(argLength).length == argLength) : "Array not built with correct length";
		return avecs.get(argLength);
	}
}
